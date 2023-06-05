package pl.mimuw.flashcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mimuw.flashcards.StatisticUpdateProducer;
import pl.mimuw.flashcards.repo.FlashcardSetRepository;
import pl.mimuw.flashcards.repo.FlashcardsRepository;
import pl.mimuw.flashcards.repo.model.FlashcardDTO;
import pl.mimuw.flashcards.repo.model.FlashcardSetDTO;

import java.util.List;

@RestController("/flashcards")
@RequiredArgsConstructor
public class FlashcardController {

    private final FlashcardSetRepository flashcardSetRepository;

    private final FlashcardsRepository flashcardsRepository;

    private final StatisticUpdateProducer statisticUpdateProducer;

    @GetMapping("/set/{setId}")
    public FlashcardSetDTO getSet(@PathVariable Long setId) {
        return flashcardSetRepository.findById(setId).orElse(null);
    }

    @GetMapping("/flashcards")
    public List<FlashcardDTO> getFlashcards(@RequestParam List<Long> flashcardIds) {
        return flashcardsRepository.findAllById(flashcardIds);
    }

    @PostMapping("/set/create")
    public Long createSet(
            @RequestParam Long creatorId,
            @RequestParam String name) {
        return flashcardSetRepository.save(
                FlashcardSetDTO.builder()
                        .creatorId(creatorId)
                        .name(name)
                        .flashcardIds(List.of())
                        .build()
        ).id();
    }

    @PostMapping("/flashcards/create")
    public Long createFlashcard(
            @RequestParam Long creatorId,
            @RequestParam String term,
            @RequestParam String definition,
            @RequestParam Long setId
    ) {
        var flashcard = flashcardsRepository.save(
                FlashcardDTO.builder()
                        .creatorId(creatorId)
                        .term(term)
                        .definition(definition)
                        .build()
        );
        flashcardSetRepository.findById(setId).ifPresent(flashcardSet -> {
            var flashcards = flashcardSet.flashcardIds();
            flashcards.add(flashcard.id());
            flashcardSetRepository.save(
                    flashcardSet.withFlashcardIds(flashcards)
            );
        });
        statisticUpdateProducer.askForStatisticUpdate();
        return flashcard.id();
    }

    // edytowanie zestawu, usuwanie fiszek i zestawów (plus wiadomość na kafkę)
}

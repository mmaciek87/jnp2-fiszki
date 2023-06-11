package pl.mimuw.flashcards.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.mimuw.flashcards.kafka.StatisticUpdateProducer;
import pl.mimuw.flashcards.repo.FlashcardSetRepository;
import pl.mimuw.flashcards.repo.FlashcardsRepository;
import pl.mimuw.flashcards.repo.model.FlashcardDTO;
import pl.mimuw.flashcards.repo.model.FlashcardSetDTO;

import java.util.List;

@Slf4j
@RestController("/flashcards")
@RequiredArgsConstructor
public class FlashcardController {

    private final FlashcardSetRepository flashcardSetRepository;

    private final FlashcardsRepository flashcardsRepository;

    private final StatisticUpdateProducer statisticUpdateProducer;

    @GetMapping("/set/{setId}")
    public FlashcardSetDTO getSet(@PathVariable String setId) {
        log.info("set endpoint was called");
        return flashcardSetRepository.findById(setId).orElse(null);
    }

    @PostMapping("/set/create")
    public String createSet(
            @RequestParam String creatorId,
            @RequestParam String name) {
        log.info("set/create endpoint was called");
        return flashcardSetRepository.save(
                FlashcardSetDTO.builder()
                        .creatorId(creatorId)
                        .name(name)
                        .flashcardIds(List.of())
                        .build()
        ).id();
    }

    @GetMapping("/set/edit")
    public void editSet(
            @RequestParam String setId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> cardsToAdd,
            @RequestParam(required = false) List<String> cardsToDelete
    ) {
        log.info("set/edit endpoint was called, id: {}", setId);
        var flashcardSetOptional = flashcardSetRepository.findById(setId);
        if (flashcardSetOptional.isEmpty()) return;
        var flashcardSet = flashcardSetOptional.get();
        var flashcards = flashcardSet.flashcardIds();

        if (name != null) {
            flashcardSet = flashcardSet.withName(name);
        }
        if (cardsToAdd != null) {
            flashcards.addAll(cardsToAdd);
        }
        if (cardsToDelete != null) {
            flashcards.removeAll(cardsToDelete);
        }
        flashcardSet = flashcardSet.withFlashcardIds(flashcards);
        flashcardSetRepository.save(flashcardSet);
        statisticUpdateProducer.setEdited(setId, cardsToAdd, cardsToDelete);
    }

    @GetMapping("/set/delete")
    public void deleteSet(
            @RequestParam String setId
    ) {
        log.info("set/delete endpoint was called, id: {}", setId);
        flashcardSetRepository.deleteById(setId);
        statisticUpdateProducer.setRemoved(setId);
    }

    @GetMapping("/flashcards")
    public List<FlashcardDTO> getFlashcards(@RequestParam List<String> flashcardIds) {
        log.info("flashcards endpoint was called");
        return flashcardsRepository.findAllById(flashcardIds);
    }
    @PostMapping("/flashcards/create")
    public String createFlashcard(
            @RequestParam String creatorId,
            @RequestParam String term,
            @RequestParam String definition,
            @RequestParam String setId
    ) {
        log.info("flashcards/create endpoint was called");
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
        return flashcard.id();
    }
    @GetMapping("/flashcards/delete")
    public void deleteFlashcard(
            @RequestParam String flashcardId
    ) {
        log.info("flashcards/delete endpoint was called, id: {}", flashcardId);
        flashcardsRepository.deleteById(flashcardId);
        statisticUpdateProducer.flashcardDeleted(flashcardId);
    }
}

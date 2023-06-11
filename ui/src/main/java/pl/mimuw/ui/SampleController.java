package pl.mimuw.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.kafka.core.KafkaTemplate;
import pl.mimuw.ui.feign.FlashcardsFeignClient;
import pl.mimuw.ui.feign.UsersFeignClient;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SampleController {

    private final FlashcardsFeignClient flashcardsFeignClient;
    private final FlashcardsRepository flashcardsRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UsersFeignClient usersFeignClient;

    @PostMapping("/set/addCards")
    public void addCardsToSet(
            @RequestHeader String userId,
            @RequestParam String setId,
            @RequestParam List<FlashcardDTO> flashcardsToAdd
    ) {
        if (!usersFeignClient.isOwner(userId, setId)) return;
        flashcardsToAdd.forEach(flashcard ->
                    flashcardsFeignClient.createFlashcard(
                            userId,
                            flashcard.term(),
                            flashcard.definition(),
                            setId
                    ));
    }

    @PostMapping("/set/create")
    public void createSet(
            @RequestHeader String userId,
            @RequestParam String name
    ) {
        var setId = flashcardsFeignClient.createSet(userId, name);
        usersFeignClient.markUserAsOwner(userId, setId);
    }
}
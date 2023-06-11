package pl.mimuw.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import pl.mimuw.ui.feign.FlashcardsFeignClient;
import pl.mimuw.ui.feign.UsersFeignClient;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SampleController {

    private final FlashcardsFeignClient flashcardsFeignClient;
    private final UsersFeignClient usersFeignClient;

    @PostMapping("/flashcard/create")
    public String createFlashcard(
            @RequestHeader String userId,
            @RequestParam String setId,
            @RequestParam String term,
            @RequestParam String definition
    ) {
        if (!usersFeignClient.isOwner(userId, setId)) {
            log.info("user {} trying to access set {}", userId, setId);
            return "";
        }
        return flashcardsFeignClient.createFlashcard(
                userId,
                term,
                definition,
                setId
        );
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
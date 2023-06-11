package pl.mimuw.ui.learn;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mimuw.ui.FlashcardDTO;

import java.util.List;

@RestController
@RequestMapping("/learn")
@RequiredArgsConstructor
public class LearnController {

    private final LearnService learnService;
    @PostMapping("/set")
    public List<FlashcardDTO> learnSet(
            @RequestHeader String userId,
            @RequestParam String setId
    ) {
        return learnService.learnSet(userId, setId);
    }

    @PostMapping("/answer")
    public boolean answer(
            @RequestHeader String userId,
            @RequestParam String flashcardId,
            @RequestParam String answer
    ) {
        return learnService.answer(userId, flashcardId, answer);
    }
}

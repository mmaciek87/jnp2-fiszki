package pl.mimuw.learn;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class LearnController {
    private final LearnService learnService;

    @GetMapping("/learn")
    public List<String> getFlashcardsInLearnOrder(
            @RequestParam String userId,
            @RequestParam List<String> flashcardsToLearn
    ) {
        return learnService.flashcardsInLearnOrder(userId, flashcardsToLearn);
    }
}

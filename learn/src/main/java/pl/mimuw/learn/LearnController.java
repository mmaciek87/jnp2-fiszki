package pl.mimuw.learn;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class LearnController {
    private final LearnService learnService;

    @GetMapping("/learn")
    public List<String> getFlashcardsInLearnOrder(
            @RequestParam String userId,
            @RequestParam List<String> flashcardsToLearn
    ) {
        log.info("/learn endpoint was called");
        return learnService.flashcardsInLearnOrder(userId, flashcardsToLearn);
    }
}

package pl.mimuw.ui.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mimuw.ui.FlashcardDTO;
import pl.mimuw.ui.TestDTO;

import java.util.List;

@FeignClient("flashcards")
public interface FlashcardsFeignClient {

    @GetMapping("/hello")
    String sayHello();

    @GetMapping("/test")
    TestDTO test(@RequestParam String name);

    @GetMapping("/set/edit")
    void editSet(
            @RequestParam Long setId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Long> cardsToAdd,
            @RequestParam(required = false) List<Long> cardsToDelete
    );

    @PostMapping("/flashcards/create")
    Long createFlashcard(
            @RequestParam Long creatorId,
            @RequestParam String term,
            @RequestParam String definition,
            @RequestParam Long setId
    );

    @PostMapping("/set/create")
    Long createSet(
            @RequestParam Long creatorId,
            @RequestParam String name
    );

    @GetMapping("/set/{setId}")
    FlashcardSetDTO getSet(@PathVariable String setId);

    @GetMapping("/flashcards")
    List<FlashcardDTO> getFlashcards(@RequestParam List<String> flashcardIds);
}

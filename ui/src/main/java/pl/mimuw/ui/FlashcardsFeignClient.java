package pl.mimuw.ui;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}

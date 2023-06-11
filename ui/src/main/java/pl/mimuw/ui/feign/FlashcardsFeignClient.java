package pl.mimuw.ui.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mimuw.ui.FlashcardDTO;

import java.util.List;

@FeignClient("flashcards")
public interface FlashcardsFeignClient {
    @GetMapping("/set/edit")
    void editSet(
            @RequestParam String setId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> cardsToAdd,
            @RequestParam(required = false) List<String> cardsToDelete
    );

    @PostMapping("/flashcards/create")
    String createFlashcard(
            @RequestParam String creatorId,
            @RequestParam String term,
            @RequestParam String definition,
            @RequestParam String setId
    );

    @PostMapping("/set/create")
    String createSet(
            @RequestParam String creatorId,
            @RequestParam String name
    );

    @GetMapping("/set/{setId}")
    FlashcardSetDTO getSet(@PathVariable String setId);

    @GetMapping("/flashcards")
    List<FlashcardDTO> getFlashcards(@RequestParam List<String> flashcardIds);
}

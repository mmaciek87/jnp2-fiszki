package pl.mimuw.ui;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("flashcards")
public interface FlashcardsFeignClient {

    @GetMapping("/hello")
    String sayHello();
}

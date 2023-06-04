package pl.mimuw.ui;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("flashcards")
public interface FlashcardsFeignClient {

    @GetMapping("/hello")
    String sayHello();

    @GetMapping("/test")
    TestDTO test(@RequestParam String name);
}

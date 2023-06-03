package pl.mimuw.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SampleController {

    private final FlashcardsFeignClient flashcardsFeignClient;
    private final FlashcardsService flashcardsService;

    @GetMapping("/hello")
    public String sayHello() {
        log.info("someone called /hello endpoint");
        return flashcardsService.getHello();
    }

    @GetMapping("/hello1")
    public String sayHello1() {
        log.info("someone called /hello1 endpoint");
        return flashcardsFeignClient.sayHello();
    }
}
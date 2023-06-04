package pl.mimuw.flashcards;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class SampleController {

    private final Clock clock;

    @GetMapping("/hello")
    public String sayHello() {
        log.info("someone called /hello endpoint");
        return "Hello, World!";
    }

    @GetMapping("/test")
    public TestDTO test(@RequestParam String name) {
        log.info("someone called /test endpoint");
        return TestDTO.builder()
                .name(name)
                .modifiedTime(clock.instant())
                .build();
    }
}

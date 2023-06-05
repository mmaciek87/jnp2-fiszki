package pl.mimuw.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SampleController {

    private final FlashcardsFeignClient flashcardsFeignClient;
    private final FlashcardsService flashcardsService;
    private final FlashcardsRepository flashcardsRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

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

    @GetMapping("/saveToDb")
    public void save() {
        log.info("someone called /saveToDb endpoint");
        flashcardsRepository.save(
                FlashcardDTO.builder()
                        .id(1L)
                        .name("jeden")
                        .build()
        );
    }

    @GetMapping("/readFromDb")
    public List<FlashcardDTO> read() {
        log.info("someone called /readFromDb endpoint");
        return flashcardsRepository.findAll();
    }

    @GetMapping("/name")
    public TestDTO test(@RequestParam String name) {
        return flashcardsFeignClient.test(name);
    }

    @GetMapping("/page")
    public String page() {
        return """
                <!DOCTYPE html>
                <html>
                    <head>
                        <title>Example</title>
                    </head>
                    <body>
                        <p>This is an example of a simple HTML page with one paragraph.</p>
                    </body>
                </html>
                """;
    }

    @GetMapping("/sendToKafka")
    public void sendToKafka() {
        kafkaTemplate.send("topic", "testMessage");
        log.info("sent test message to kafka");
    }
}
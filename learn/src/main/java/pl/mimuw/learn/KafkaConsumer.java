package pl.mimuw.learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
    @KafkaListener(id = "id", topics = "${flashcards.topic}")
    public void listen(String in) {
        log.info("got message: {}", in);
    }
}

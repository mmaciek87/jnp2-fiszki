package pl.mimuw.learn;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateStatisticsConsumer {
    private final UpdateStatisticsService updateStatisticsService;
    private final ObjectMapper objectMapper;

    @KafkaListener(id = "${spring.application.name}", topics = "${learn.topic}")
    public void handleAnswer(String message) {
        try {
            log.info("received a message via MQ: {}", message);
            var userAnswer = objectMapper.readValue(message, UserAnswer.class);
            updateStatisticsService.handleAnswer(
                    userAnswer.userId(),
                    userAnswer.flashcardId(),
                    userAnswer.correct()
            );
        } catch (Exception e) {
            log.error("exception occurred while parsing json", e);
        }
    }
}

package pl.mimuw.ui.learn;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserAnswerProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${learn.topic:learn}")
    private String topic;

    public void notifyAboutAnswer(UserAnswer userAnswer) {
        try {
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(userAnswer));
        } catch (Exception e) {
            log.error("got some error while sending message to update answer", e);
        }
    }

    @Jacksonized
    @Builder
    public record UserAnswer(
            String userId,
            String flashcardId,
            Boolean correct
    ) { }
}

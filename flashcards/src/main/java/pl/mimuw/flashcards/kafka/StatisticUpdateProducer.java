package pl.mimuw.flashcards.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import pl.mimuw.flashcards.kafka.messages.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticUpdateProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    public void flashcardDeleted(String flashcardId){
        var message = new FlashcardDeleteMessage();
        message.setFlashcardId(flashcardId);
        try {
            kafkaTemplate.send("flashcardDeleted", objectMapper.writeValueAsString(message));
            log.info("Kafka message: {}", objectMapper.writeValueAsString(message));
        } catch(Exception e) {
            log.info("Error while sending kafka message: ", e);
        }
    }

    public void setEdited(String setId, List<String> cardsToAdd, List<String> cardsToDelete) {
        var message = new SetEditMessage();
        message.setSetId(setId);
        message.setAddedIds(cardsToAdd);
        message.setRemovedIds(cardsToDelete);
        try {
            kafkaTemplate.send("setEdited", objectMapper.writeValueAsString(message));
            log.info("Kafka message: {}", objectMapper.writeValueAsString(message));
        } catch(Exception e) {
            log.info("Error while sending kafka message: ", e);
        }
    }

    public void setRemoved(String setId) {
        var message = new SetDeleteMessage();
        message.setSetId(setId);
        try {
            kafkaTemplate.send("setDeleted", objectMapper.writeValueAsString(message));
            log.info("Kafka message: {}", objectMapper.writeValueAsString(message));
        } catch(Exception e) {
            log.info("Error while sending kafka message: ", e);
        }
    }
}

package pl.mimuw.flashcards.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import pl.mimuw.flashcards.kafka.messages.*;

@Slf4j
@Component
public class StatisticUpdateProducer {

    private static KafkaTemplate<String, FlashcardDeleteMessage> fDeleteTemplate;
    private static KafkaTemplate<String, SetDeleteMessage> sDeleteTemplate;
    private static KafkaTemplate<String, SetEditMessage> sEditTemplate;
    private static final String topicName = "flashcards";


    public static void flashcardDeleted(String flashcardId) {
        var message = new FlashcardDeleteMessage();
        message.setFlashcardId(flashcardId);
        fDeleteTemplate.send(topicName, message);
        log.info("Kafka message: deleted flashcard {}", flashcardId);
    }

    public static void setEdited(String setId, List<String> cardsToAdd, List<String> cardsToDelete) {
        var message = new SetEditMessage();
        message.setSetId(setId);
        message.setAddedIds(cardsToAdd);
        message.setRemovedIds(cardsToDelete);
        sEditTemplate.send(topicName, message);
        log.info("Kafka message: edited set {}", setId);
    }

    public static void setRemoved(String setId) {
        var message = new SetDeleteMessage();
        message.setSetId(setId);
        sDeleteTemplate.send(topicName, message);
        log.info("Kafka message: removed set {}", setId);
    }
}

package pl.mimuw.flashcards.kafka;

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

    private final KafkaTemplate<String, FlashcardDeleteMessage> fDeleteTemplate;
    private final KafkaTemplate<String, SetDeleteMessage> sDeleteTemplate;
    private final KafkaTemplate<String, SetEditMessage> sEditTemplate;
    private static final String TOPIC_NAME = "flashcards";


    public void flashcardDeleted(String flashcardId) {
        var message = new FlashcardDeleteMessage();
        message.setFlashcardId(flashcardId);
        fDeleteTemplate.send(TOPIC_NAME, message);
        log.info("Kafka message: deleted flashcard {}", flashcardId);
    }

    public void setEdited(String setId, List<Long> cardsToAdd, List<Long> cardsToDelete) {
        var message = new SetEditMessage();
        message.setSetId(setId);
        message.setAddedIds(cardsToAdd);
        message.setRemovedIds(cardsToDelete);
        sEditTemplate.send(TOPIC_NAME, message);
        log.info("Kafka message: edited set {}", setId);
    }

    public void setRemoved(String setId) {
        var message = new SetDeleteMessage();
        message.setSetId(setId);
        sDeleteTemplate.send(TOPIC_NAME, message);
        log.info("Kafka message: removed set {}", setId);
    }
}

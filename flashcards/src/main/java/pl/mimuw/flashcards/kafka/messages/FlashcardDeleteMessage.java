package pl.mimuw.flashcards.kafka.messages;

import lombok.Data;

@Data
public class FlashcardDeleteMessage {
    private Long flashcardId;
}

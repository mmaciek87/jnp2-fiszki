package pl.mimuw.flashcards.kafka.messages;

import lombok.Data;

@Data
public class SetDeleteMessage {
    private Long setId;
}

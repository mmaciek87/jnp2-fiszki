package pl.mimuw.flashcards.kafka.messages;

import lombok.Data;
import java.util.List;

@Data
public class SetEditMessage {
    private Long setId;
    private List<Long> addedIds;
    private List<Long> removedIds;
}

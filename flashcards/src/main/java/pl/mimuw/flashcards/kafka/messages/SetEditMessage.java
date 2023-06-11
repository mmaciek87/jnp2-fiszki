package pl.mimuw.flashcards.kafka.messages;

import lombok.Data;
import java.util.List;

@Data
public class SetEditMessage {
    private String setId;
    private List<String> addedIds;
    private List<String> removedIds;
}

package pl.mimuw.flashcards.repo.model;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
@With
public record FlashcardSetDTO(
        @Id String id,
        String creatorId,
        String name,
        List<String> flashcardIds
) {
}

package pl.mimuw.flashcards.repo.model;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
@With
public record FlashcardSetDTO(
        @Id Long id,
        Long creatorId,
        String name,
        List<Long> flashcardIds
) {
}

package pl.mimuw.flashcards.repo.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record FlashcardDTO(
        @Id Long id,
        Long creatorId,
        String term,
        String definition
) {
}

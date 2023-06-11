package pl.mimuw.flashcards.repo.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record FlashcardDTO(
        @Id String id,
        String creatorId,
        String term,
        String definition
) {
}

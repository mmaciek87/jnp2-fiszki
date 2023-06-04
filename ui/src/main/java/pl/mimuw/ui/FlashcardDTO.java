package pl.mimuw.ui;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record FlashcardDTO(
        @Id Long id,
        String name
) {
}

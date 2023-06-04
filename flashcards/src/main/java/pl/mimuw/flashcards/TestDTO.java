package pl.mimuw.flashcards;

import lombok.Builder;

import java.time.Instant;

@Builder
public record TestDTO(
        String name,
        Instant modifiedTime
) {
}

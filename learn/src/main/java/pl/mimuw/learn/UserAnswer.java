package pl.mimuw.learn;

import lombok.extern.jackson.Jacksonized;

@Jacksonized
public record UserAnswer(
        String userId,
        String flashcardId,
        Boolean correct
) {
}

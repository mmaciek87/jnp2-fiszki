package pl.mimuw.learn;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.Id;

@Builder
@With
public record UserPerformanceDTO(
        @Id String id,
        String userId,
        String flashcardId,
        Integer numberOfHits,
        Integer numberOfTries
) {
    public Double getHitRate() {
        return numberOfTries == 0
                ? 0
                : numberOfHits.doubleValue() / numberOfTries.doubleValue();
    }

    public static String generateId(String userId, String flashcardId) {
        return userId + "#" + flashcardId;
    }
}

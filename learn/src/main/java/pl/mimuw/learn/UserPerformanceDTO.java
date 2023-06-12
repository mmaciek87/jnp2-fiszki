package pl.mimuw.learn;

import lombok.Builder;
import lombok.With;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

@Builder
@With
@Slf4j
public record UserPerformanceDTO(
        @Id String id,
        String userId,
        String flashcardId,
        Integer numberOfHits,
        Integer numberOfTries
) {
    public Double getHitRate() {
        Double result = numberOfTries == 0
                ? 0
                : numberOfHits.doubleValue() / numberOfTries.doubleValue();
        log.info("got hit rate = {}", result);
        return result;
    }

    public static String generateId(String userId, String flashcardId) {
        return userId + "#" + flashcardId;
    }
}

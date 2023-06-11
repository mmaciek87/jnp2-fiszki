package pl.mimuw.learn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static pl.mimuw.learn.UserPerformanceDTO.generateId;

@Service
@RequiredArgsConstructor
public class UpdateStatisticsService {
    private final UserPerformanceRepository userPerformanceRepository;

    public void handleAnswer(String userId, String flashcardId, boolean correct) {
        userPerformanceRepository.findById(generateId(userId, flashcardId)).ifPresentOrElse(performance ->
                userPerformanceRepository.save(
                    performance
                        .withNumberOfTries(performance.numberOfTries() + 1)
                        .withNumberOfHits(performance.numberOfHits() + (correct ? 1 : 0))
        ), () -> userPerformanceRepository.save(
                UserPerformanceDTO.builder()
                        .id(generateId(userId, flashcardId))
                        .userId(userId)
                        .flashcardId(flashcardId)
                        .numberOfHits(correct ? 1 : 0)
                        .numberOfTries(1)
                        .build()
        ));
    }
}

package pl.mimuw.learn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStatisticsService {
    private final UserPerformanceRepository userPerformanceRepository;

    public void handleAnswer(String userId, String flashcardId, boolean correct) {
        userPerformanceRepository.findById(UserPerformanceDTO.generateId(userId, flashcardId)).ifPresent(performance ->
                userPerformanceRepository.save(
                    performance
                        .withNumberOfTries(performance.numberOfTries() + 1)
                        .withNumberOfHits(performance.numberOfHits() + (correct ? 1 : 0))
        ));
    }
}

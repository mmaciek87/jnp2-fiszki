package pl.mimuw.learn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LearnService {
    private final UserPerformanceRepository userPerformanceRepository;

    public List<String> flashcardsInLearnOrder(
            String userId,
            List<String> flashcardIds
    ) {
        flashcardIds.forEach(flashcardId -> {
            userPerformanceRepository.findById(flashcardId).ifPresentOrElse(
                    flashcard -> {},
                    () ->
                        userPerformanceRepository.save(
                                UserPerformanceDTO.builder()
                                        .id(UserPerformanceDTO.generateId(userId, flashcardId))
                                        .flashcardId(flashcardId)
                                        .numberOfHits(0)
                                        .numberOfTries(0)
                                        .userId(userId)
                                        .build()
                        )
            );
        });
        return userPerformanceRepository.findAllByUserIdAndFlashcardIdIn(userId, flashcardIds).stream()
                .sorted(Comparator.comparing(UserPerformanceDTO::getHitRate))
                .map(UserPerformanceDTO::flashcardId)
                .toList();
    }
}

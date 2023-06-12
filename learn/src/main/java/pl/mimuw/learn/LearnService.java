package pl.mimuw.learn;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearnService {
    private final UserPerformanceRepository userPerformanceRepository;

    public List<String> flashcardsInLearnOrder(
            String userId,
            List<String> flashcardIds
    ) {
        flashcardIds.forEach(flashcardId -> {
            userPerformanceRepository.findById(UserPerformanceDTO.generateId(userId, flashcardId)).ifPresentOrElse(
                    flashcard -> {
                        log.info("flashcard with id = {} is present", flashcardId);
                    },
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
        var x = userPerformanceRepository.findAllByUserIdAndFlashcardIdIn(userId, flashcardIds);
        log.info("result before sorting: {}", printList(x));
        x.sort(Comparator.comparingDouble(UserPerformanceDTO::getHitRate));
        log.info("result after sorting: {}", printList(x));
        var result = x.stream().map(UserPerformanceDTO::flashcardId).toList();
        log.info("result ids: {}", result);
        return result;
    }

    private <T> String printList(List<T> list) {
        return list.stream()
                .map(T::toString)
                .collect(Collectors.joining(","));
    }
}

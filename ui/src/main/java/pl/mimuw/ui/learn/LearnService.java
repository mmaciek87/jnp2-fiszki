package pl.mimuw.ui.learn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mimuw.ui.FlashcardDTO;
import pl.mimuw.ui.feign.FlashcardsFeignClient;
import pl.mimuw.ui.feign.LearnFeignClient;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LearnService {
    private final FlashcardsFeignClient flashcardsFeignClient;
    private final LearnFeignClient learnFeignClient;
    private final UserAnswerProducer userAnswerProducer;

    public List<FlashcardDTO> learnSet(
        String userId,
        String setId
    ) {
        var flashcardsToLearnIds = flashcardsFeignClient.getSet(setId).flashcardIds();
        var flashcardsToLearn = flashcardsFeignClient.getFlashcards(flashcardsToLearnIds);
        var flashcardsGroupedById = flashcardsToLearn.stream().collect(Collectors.toMap(FlashcardDTO::id, Function.identity()));
        return learnFeignClient.getFlashcardsInLearnOrder(userId, flashcardsToLearnIds).stream().map(flashcardsGroupedById::get).toList();
    }

    public boolean answer(
            String userId,
            String flashcardId,
            String answer
    ) {
        AtomicBoolean correct = new AtomicBoolean(false);
        flashcardsFeignClient.getFlashcards(List.of(flashcardId)).stream().findFirst().ifPresent(flashcard -> {
            correct.set(flashcard.definition().compareTo(answer) == 0);
            userAnswerProducer.notifyAboutAnswer(UserAnswerProducer.UserAnswer.builder()
                    .userId(userId)
                    .flashcardId(flashcardId)
                    .correct(correct.get())
                    .build());
        });
        return correct.get();
    }
}

package pl.mimuw.flashcards.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mimuw.flashcards.repo.model.FlashcardSetDTO;

@Repository
public interface FlashcardSetRepository extends MongoRepository<FlashcardSetDTO, Long> {
}

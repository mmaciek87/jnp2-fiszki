package pl.mimuw.flashcards.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mimuw.flashcards.repo.model.FlashcardDTO;

@Repository
public interface FlashcardsRepository extends MongoRepository<FlashcardDTO, Long> {
}

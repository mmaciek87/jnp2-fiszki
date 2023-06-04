package pl.mimuw.ui;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardsRepository extends MongoRepository<FlashcardDTO, Long> {
}

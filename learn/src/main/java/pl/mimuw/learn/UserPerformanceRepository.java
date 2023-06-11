package pl.mimuw.learn;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPerformanceRepository extends MongoRepository<UserPerformanceDTO, String> {
    List<UserPerformanceDTO> findAllByUserIdAndFlashcardIdIn(String userId, List<String> flashcardId);
}

package pl.mimuw.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetOwnershipRepository extends MongoRepository<SetOwnershipDTO, String> {
    List<SetOwnershipDTO> findAllByOwnerId(String ownerId);
}

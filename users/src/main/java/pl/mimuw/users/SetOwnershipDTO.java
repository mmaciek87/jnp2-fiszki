package pl.mimuw.users;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record SetOwnershipDTO(
        @Id String setId,
        String ownerId
) {
}

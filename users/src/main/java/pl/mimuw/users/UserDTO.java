package pl.mimuw.users;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record UserDTO(
        @Id String userId,
        String nickname
) {
}

package pl.mimuw.users;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record UserDTO(
        @Id Long userId,
        String nickname
) {
}

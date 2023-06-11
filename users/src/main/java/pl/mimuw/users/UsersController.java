package pl.mimuw.users;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
@RequiredArgsConstructor
public class UsersController {
    private final UsersRepository usersRepository;

    @PostMapping("/create")
    public String createUser(@RequestParam String nickname) {
        return usersRepository.save(
                UserDTO.builder()
                        .nickname(nickname)
                        .build()
        ).userId();
    }
}

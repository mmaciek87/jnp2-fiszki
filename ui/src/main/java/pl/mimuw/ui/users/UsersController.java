package pl.mimuw.ui.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mimuw.ui.feign.UsersFeignClient;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {
    private final UsersFeignClient usersFeignClient;

    @PostMapping("/create")
    public String createAccount(String nickname) {
        return usersFeignClient.createUser(nickname);
    }
}

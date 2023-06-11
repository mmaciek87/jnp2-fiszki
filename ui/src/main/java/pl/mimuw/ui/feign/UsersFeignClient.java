package pl.mimuw.ui.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("users")
public interface UsersFeignClient {
    @GetMapping("/isOwner")
    boolean isOwner(
            @RequestParam String userId,
            @RequestParam String setId
    );

    @PostMapping("/markUserAsOwner")
    void markUserAsOwner(
            @RequestParam String userId,
            @RequestParam String setId
    );
}

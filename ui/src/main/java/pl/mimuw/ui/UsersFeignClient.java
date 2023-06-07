package pl.mimuw.ui;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("users")
public interface UsersFeignClient {
    @GetMapping("/isOwner")
    boolean isOwner(
            @RequestParam Long userId,
            @RequestParam Long setId
    );
}

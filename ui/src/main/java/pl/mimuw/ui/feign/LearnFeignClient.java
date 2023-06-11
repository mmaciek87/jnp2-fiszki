package pl.mimuw.ui.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("learn")
public interface LearnFeignClient {

    @GetMapping("/learn")
    List<String> getFlashcardsInLearnOrder(
            @RequestParam String userId,
            @RequestParam List<String> flashcardsToLearn
    );
}

package pl.mimuw.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FlashcardsService {
    private final RestTemplate restTemplate;

    public String getHello() {
        return restTemplate.getForObject("http://flashcards/hello", String.class);
    }
}

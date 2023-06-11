package pl.mimuw.flashcards.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializerConfig {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

package ru.vadim.tripadvisor.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;
import ru.vadim.tripadvisor.dto.LocalRecommendations;

@RequiredArgsConstructor
public class LocalRecommendationServiceClient {

    private final RestClient client;

    public LocalRecommendations getLocalRecommendations(String airportCode) {
        return this.client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(LocalRecommendations.class);
    }
}

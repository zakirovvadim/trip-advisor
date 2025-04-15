package ru.vadim.tripadvisor.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;
import ru.vadim.tripadvisor.dto.Weather;

@RequiredArgsConstructor
public class WeatherServiceClient {

    private final RestClient client;

    public Weather getAccommodations(String airportCode) {
        return this.client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(Weather.class);
    }
}

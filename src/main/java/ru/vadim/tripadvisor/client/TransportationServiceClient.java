package ru.vadim.tripadvisor.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;
import ru.vadim.tripadvisor.dto.Transportation;

@RequiredArgsConstructor
public class TransportationServiceClient {

    private final RestClient client;

    public Transportation getTransportation(String airportCode) {
        return this.client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(Transportation.class);
    }
}

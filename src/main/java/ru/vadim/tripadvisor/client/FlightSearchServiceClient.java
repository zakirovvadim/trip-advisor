package ru.vadim.tripadvisor.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import ru.vadim.tripadvisor.dto.Flight;

import java.util.List;

@RequiredArgsConstructor
public class FlightSearchServiceClient {

    private final RestClient client;

    public List<Flight> getFlights(String departure, String arrival) {
        return client.get()
                .uri("/{departure}/{arrival}", departure, arrival)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Flight>>() {
                });
    }
}

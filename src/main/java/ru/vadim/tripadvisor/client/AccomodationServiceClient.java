package ru.vadim.tripadvisor.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import ru.vadim.tripadvisor.dto.Accomodation;

import java.util.List;

@RequiredArgsConstructor
public class AccomodationServiceClient {

    private final RestClient client;

    public List<Accomodation> getAccommodations(String airportCode) {
        return this.client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Accomodation>>() {});
    }
}

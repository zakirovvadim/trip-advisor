package ru.vadim.tripadvisor.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;
import ru.vadim.tripadvisor.dto.FlightReservationRequest;
import ru.vadim.tripadvisor.dto.FlightReservationResponse;

@RequiredArgsConstructor
public class FlightResrvationServiceClient {

    private final RestClient client;

    public FlightReservationResponse getFlightReservations(FlightReservationRequest request) {
        return client.post()
                .body(request)
                .retrieve()
                .body(FlightReservationResponse.class);
    }
}

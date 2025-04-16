package ru.vadim.tripadvisor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vadim.tripadvisor.client.FlightResrvationServiceClient;
import ru.vadim.tripadvisor.client.FlightSearchServiceClient;
import ru.vadim.tripadvisor.dto.Flight;
import ru.vadim.tripadvisor.dto.FlightReservationRequest;
import ru.vadim.tripadvisor.dto.FlightReservationResponse;
import ru.vadim.tripadvisor.dto.TripReservationRequest;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class TripReservationService {

    private final FlightSearchServiceClient flightSearchServiceClient;
    private final FlightResrvationServiceClient flightResrvationServiceClient;

    public FlightReservationResponse reserve(TripReservationRequest request) {
        var flights = flightSearchServiceClient.getFlights(request.departure(), request.arrival());
        Flight flight = flights.stream()
                .min(Comparator.comparing(Flight::price))
                .orElseThrow(() -> new IllegalStateException("No flight found"));
        var reservation = new FlightReservationRequest(request.departure(), request.arrival(), flight.flightNumber(), flight.date());
        return flightResrvationServiceClient.getFlightReservations(reservation);
    }
}

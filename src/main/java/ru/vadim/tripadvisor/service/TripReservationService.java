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
        var flights = this.flightSearchServiceClient.getFlights(request.departure(), request.arrival());
        var bestDeal = flights.stream().min(Comparator.comparingInt(Flight::price));
        var flight = bestDeal.orElseThrow(() -> new IllegalStateException("no flights found"));
        var reservationRequest = new FlightReservationRequest(request.departure(), request.arrival(), flight.flightNumber(), request.date());
        return this.flightResrvationServiceClient.getFlightReservations(reservationRequest);
    }
}

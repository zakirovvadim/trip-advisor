package ru.vadim.tripadvisor.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.vadim.tripadvisor.client.*;
import ru.vadim.tripadvisor.dto.TripPlan;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class TripPlanService {

    public static final Logger log = LoggerFactory.getLogger(TripPlanService.class);
    private final EventServiceClient eventServiceClient;
    private final WeatherServiceClient weatherServiceClient;
    private final AccomodationServiceClient accomodationServiceClient;
    private final TransportServiceClient transportServiceClient;
    private final LocalRecommendationServiceClient localRecommendations;
    private final ExecutorService executor;

    public TripPlan getTripPlan(String airportCode) {
        var events = this.executor.submit(() -> this.eventServiceClient.getEvents(airportCode));
        var weather = this.executor.submit(() -> this.weatherServiceClient.getWeather(airportCode));
        var accommodations = this.executor.submit(() -> this.accomodationServiceClient.getAccommodations(airportCode));
        var transportation = this.executor.submit(() -> this.transportServiceClient.getTransportation(airportCode));
        var recommendations = this.executor.submit(() -> this.localRecommendations.getLocalRecommendations(airportCode));
        return new TripPlan(
                airportCode,
                getOrElse(accommodations, Collections.emptyList()),
                getOrElse(weather, null),
                getOrElse(events, Collections.emptyList()),
                getOrElse(recommendations, null),
                getOrElse(transportation, null)
                );
    }

    private <T> T getOrElse(Future<T> future, T defaultValue) {
        try {
            future.get();
        } catch (Exception e) {
            log.error("error", e);
        }
        return defaultValue;
    }
}

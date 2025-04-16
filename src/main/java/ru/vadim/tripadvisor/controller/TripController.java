package ru.vadim.tripadvisor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vadim.tripadvisor.dto.FlightReservationResponse;
import ru.vadim.tripadvisor.dto.TripPlan;
import ru.vadim.tripadvisor.dto.TripReservationRequest;
import ru.vadim.tripadvisor.service.TripPlanService;
import ru.vadim.tripadvisor.service.TripReservationService;

@RestController
@RequestMapping("trip")
@RequiredArgsConstructor
public class TripController {

    private final TripReservationService tripReservationService;
    private final TripPlanService tripPlanService;

    @GetMapping("{airportCode}")
    public TripPlan getTripPlan(@PathVariable String airportCode) {
        return tripPlanService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request) {
        return tripReservationService.reserve(request);
    }
}

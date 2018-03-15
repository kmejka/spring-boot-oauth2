package com.kmejka.flights.controller;

import java.util.Collection;
import java.util.Optional;

import com.kmejka.flights.model.Flight;
import com.kmejka.flights.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flights/v1")
public class FlightsController {

    private FlightService flightService;

    public FlightsController(final FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<Collection<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("id") final String id) {
        final Optional<Flight> flightById = flightService.getFlightById(id);
        if (!flightById.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightById.get());
    }
}

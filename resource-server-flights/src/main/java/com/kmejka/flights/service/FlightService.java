package com.kmejka.flights.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import com.kmejka.flights.model.Flight;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    private static final Collection<Flight> FLIGHTS = Arrays.asList(
            Flight.builder()
                    .departureDate(Date.from(Instant.parse("2018-04-03T10:15:30.00Z")))
                    .departure("MAD")
                    .arrivalDate(Date.from(Instant.parse("2018-04-03T11:05:30.00Z")))
                    .destination("AMS")
                    .id("1") 
                    .build(), 
            Flight.builder()
                    .departureDate(Date.from(Instant.parse("2018-03-16T12:15:30.00Z")))
                    .departure("BER")
                    .arrivalDate(Date.from(Instant.parse("2018-03-16T13:55:30.00Z")))
                    .destination("LON")
                    .id("2")
                    .build());

    public Collection<Flight> getAllFlights() {
        return FLIGHTS;
    }

    public Optional<Flight> getFlightById(@NonNull final String id) {
        return FLIGHTS.stream().filter(f -> f.getId().equals(id)).findFirst();
    }
}

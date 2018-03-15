package com.kmejka.bookings.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import com.kmejka.bookings.client.flights.Flight;
import com.kmejka.bookings.client.flights.FlightsClient;
import com.kmejka.bookings.model.Booking;
import com.kmejka.bookings.model.Money;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private FlightsClient flightsClient;

    public BookingService(final FlightsClient flightsClient) {
        this.flightsClient = flightsClient;
    }

    private static final Collection<Booking> BOOKINGS = Arrays.asList(
            Booking.builder()
                    .creationDate(new Date())
                    .flight(Flight.builder().id("1").build())
                    .id("1")
                    .price(Money.of(BigDecimal.valueOf(130.0), "EUR"))
                    .airline("KLM")
                    .build(),
            Booking.builder()
                    .creationDate(new Date())
                    .flight(Flight.builder().id("2").build())
                    .id("2")
                    .price(Money.of(BigDecimal.valueOf(70.0), "EUR"))
                    .airline("Lufthansa")
                    .build());

    public Collection<Booking> getAllBookings() {
        return BOOKINGS.stream()
                .peek(b -> Optional.ofNullable(flightsClient.getById(b.getFlight().getId())).ifPresent(b::setFlight))
                .collect(Collectors.toList());
    }

    public Optional<Booking> getBookingById(@NonNull final String id) {
        return BOOKINGS.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .map(b -> {
                    Optional.ofNullable(flightsClient.getById(b.getFlight().getId())).ifPresent(b::setFlight);
                    return b;
                });
    }
}

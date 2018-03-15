package com.kmejka.bookings.controller;

import java.util.Collection;
import java.util.Optional;

import com.kmejka.bookings.model.Booking;
import com.kmejka.bookings.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings/v1")
public class BookingsController {

    private BookingService bookingService;

    public BookingsController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<Collection<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") final String id) {
        final Optional<Booking> bookingById = bookingService.getBookingById(id);
        if (!bookingById.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookingById.get());
    }
}

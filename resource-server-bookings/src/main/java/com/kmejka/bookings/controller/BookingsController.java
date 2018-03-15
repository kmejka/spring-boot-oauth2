package com.kmejka.bookings.controller;

import java.util.Collection;
import java.util.Optional;

import com.kmejka.bookings.model.Booking;
import com.kmejka.bookings.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings/v1")
@Slf4j
public class BookingsController {

    private BookingService bookingService;

    public BookingsController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<Collection<Booking>> getAllBookings() {
        logSecurityData();
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") final String id) {
        logSecurityData();
        final Optional<Booking> bookingById = bookingService.getBookingById(id);
        if (!bookingById.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookingById.get());
    }

    private void logSecurityData() {
        final OAuth2Authentication authenticationData = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        final OAuth2AuthenticationDetails authenticationDataDetails = (OAuth2AuthenticationDetails) authenticationData.getDetails();

        log.debug("Bookings logged in client token: '{}'", authenticationDataDetails.getTokenValue());
        log.debug("Bookings logged in client id: '{}'", authenticationData.getOAuth2Request().getClientId());
        log.debug("Bookings logged in client scopes: '{}'", authenticationData.getOAuth2Request().getScope());

        log.debug("Bookings logged in client from principal: '{}'", authenticationData.getPrincipal());
        log.debug("Bookings logged in client authorities: '{}'", authenticationData.getAuthorities());
    }
}

package com.kmejka.flights.controller;

import java.util.Collection;
import java.util.Optional;

import com.kmejka.flights.model.Flight;
import com.kmejka.flights.service.FlightService;
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
@RequestMapping("/api/flights/v1")
@Slf4j
public class FlightsController {

    private FlightService flightService;

    public FlightsController(final FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<Collection<Flight>> getAllFlights() {
        logSecurityData();
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("id") final String id) {
        logSecurityData();
        final Optional<Flight> flightById = flightService.getFlightById(id);
        if (!flightById.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightById.get());
    }

    private void logSecurityData() {
        final OAuth2Authentication authenticationData = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        final OAuth2AuthenticationDetails authenticationDataDetails = (OAuth2AuthenticationDetails) authenticationData.getDetails();

        log.debug("Flights logged in client token: '{}'", authenticationDataDetails.getTokenValue());
        log.debug("Flights logged in client id: '{}'", authenticationData.getOAuth2Request().getClientId());
        log.debug("Flights logged in client scopes: '{}'", authenticationData.getOAuth2Request().getScope());

        log.debug("Flights logged in client from principal: '{}'", authenticationData.getPrincipal());
        log.debug("Flights logged in client authorities: '{}'", authenticationData.getAuthorities());
    }
}

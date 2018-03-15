package com.kmejka.client.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import com.kmejka.client.client.bookings.Booking;
import com.kmejka.client.client.bookings.BookingsClient;
import com.kmejka.client.model.Customer;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private BookingsClient bookingsClient;

    public CustomerService(final BookingsClient bookingsClient) {
        this.bookingsClient = bookingsClient;
    }

    public Optional<Customer> getById(@NonNull final String customerId) {
        return CUSTOMERS.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .map(c -> {
                    Optional.ofNullable(bookingsClient.getById(c.getBooking().getId())).ifPresent(c::setBooking);
                    return c;
                });
    }

    private static final Collection<Customer> CUSTOMERS = Arrays.asList(
            Customer.builder()
                    .id("1")
                    .email("someemail@example.com")
                    .firstName("John")
                    .lastName("Person")
                    .booking(Booking.builder().id("1").build())
                    .build(),
            Customer.builder()
                    .id("2")
                    .email("otheremail@example.com")
                    .firstName("Jack")
                    .lastName("Animal")
                    .build()
    );
}

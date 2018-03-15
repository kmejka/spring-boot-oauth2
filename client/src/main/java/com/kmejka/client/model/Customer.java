package com.kmejka.client.model;

import com.kmejka.client.client.bookings.Booking;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Booking booking;
}

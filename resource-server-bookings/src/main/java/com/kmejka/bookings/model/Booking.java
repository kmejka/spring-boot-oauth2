package com.kmejka.bookings.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kmejka.bookings.client.flights.Flight;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Booking {
    private String id;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    private Date creationDate;
    private Money price;
    private Flight flight;
    private String airline;
}

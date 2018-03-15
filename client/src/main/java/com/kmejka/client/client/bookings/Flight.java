package com.kmejka.client.client.bookings;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Flight {
    private String id;
    private String departure;
    private String destination;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    private Date departureDate;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    private Date arrivalDate;
}

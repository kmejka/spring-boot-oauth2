package com.kmejka.client.client.bookings;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Booking {
    private String id;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    private Date creationDate;
    private String customerId;
    private Money price;
    private Flight flight;
    private String airline;
}

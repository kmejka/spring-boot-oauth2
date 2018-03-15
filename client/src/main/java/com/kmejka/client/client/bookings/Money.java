package com.kmejka.client.client.bookings;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Money {

    private BigDecimal amount;
    private String currencyCode;

}

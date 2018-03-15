package com.kmejka.flights.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Money {
    private BigDecimal amount;
    private String currencyCode;
}

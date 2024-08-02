package com.springboot.shopbubu.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@AllArgsConstructor
public enum ShipPrice {
    SHIP_PRICE(new BigDecimal("20.000"));

    private final BigDecimal value;

}

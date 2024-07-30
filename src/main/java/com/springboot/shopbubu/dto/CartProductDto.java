package com.springboot.shopbubu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class CartProductDto {
    private Integer id;
    private BigDecimal cartPrice;
    private Integer productId;
    private Integer quantity;
}

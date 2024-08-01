package com.springboot.shopbubu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class OrderItemDto {
    private Integer id;
    private Integer productId;
    private Integer quantity;
    private BigDecimal price;

}

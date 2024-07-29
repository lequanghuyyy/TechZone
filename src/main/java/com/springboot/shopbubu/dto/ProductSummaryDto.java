package com.springboot.shopbubu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class ProductSummaryDto {
    private String productCode;
    private String productName;
    private String image;
    private Integer numberPurchase;
    private BigDecimal price;
}

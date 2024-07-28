package com.springboot.shopbubu.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDetailDto {
    private Long id;
    private String description;
    private String isFeatured;
    private String isNew;
    private Integer stockQuantity;
}

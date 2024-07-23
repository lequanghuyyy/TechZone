package com.springboot.shopbubu.dto;

import com.springboot.shopbubu.entity.ProductEntity;
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
    private ProductDto product;
}

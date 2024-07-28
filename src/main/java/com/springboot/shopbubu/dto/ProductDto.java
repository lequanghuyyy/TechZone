package com.springboot.shopbubu.dto;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto extends AbstractDto {
    private String productCode;
    private String productName;
    private String image;
    private Integer numberPurchase;
    private BigDecimal price;
    private String categoryName;
    private ProductDetailDto productDetail;
}

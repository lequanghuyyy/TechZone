package com.springboot.shopbubu.dto;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewDto extends AbstractDto{
    private Integer rating;
    private String comment;
    private ProductDetailDto productDetail;
    private CustomerDetailDto customerDetail;
}

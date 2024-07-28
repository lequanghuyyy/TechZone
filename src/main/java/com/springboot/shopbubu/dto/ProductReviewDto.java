package com.springboot.shopbubu.dto;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewDto extends AbstractDto{
    private Integer rating;
    private String comment;
    private Integer productDetailId;
    private Integer customerDetailId;
}

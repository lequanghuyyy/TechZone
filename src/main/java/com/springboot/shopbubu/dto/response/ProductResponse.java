package com.springboot.shopbubu.dto.response;

import com.springboot.shopbubu.dto.ProductDetailDto;
import com.springboot.shopbubu.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {
    private ProductDetailDto productDetail;
    private ProductDto productDto;
}

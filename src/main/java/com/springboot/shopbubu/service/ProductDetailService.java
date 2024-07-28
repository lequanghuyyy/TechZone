package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.ProductDetailDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductDetailService {
    ProductDetailDto findById(Long id);
    ProductDetailDto create(ProductDetailDto productDetailDto);
    ProductDetailDto update(ProductDetailDto productDetailDto);
    void deleteById(Long id);
}

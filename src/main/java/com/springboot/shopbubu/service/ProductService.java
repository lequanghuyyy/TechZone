package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    ProductDto findById(Long id);
    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto);
    void deleteById(Long id);
}

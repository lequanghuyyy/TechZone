package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> findAll();
    ProductDto create(ProductDto productDto);
    ProductDto findById(Long id);
    ProductDto update(ProductDto productDto);
    void deleteById(Long id);
}

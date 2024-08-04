package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.dto.paging.PageDto;
import com.springboot.shopbubu.dto.paging.ProductSearchRequest;
import com.springboot.shopbubu.dto.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    PageDto<ProductDto> findAll(ProductSearchRequest productRequest);
    ProductDto create(ProductDto productDto);
    ProductDto findById(Long id);
    ProductDto update(ProductDto productDto);
    void deleteById(Long id);
}

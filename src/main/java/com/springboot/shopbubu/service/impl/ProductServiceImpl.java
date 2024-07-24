package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.repository.ProductRepository;
import com.springboot.shopbubu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> findAll() {
        return List.of();
    }

    @Override
    public ProductDto findById(Long id) {
        return null;
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

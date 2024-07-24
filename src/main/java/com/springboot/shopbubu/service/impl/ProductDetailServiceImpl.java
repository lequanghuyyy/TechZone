package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl {
    private final ProductDetailRepository productDetailRepository;
    @Autowired
    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }
}

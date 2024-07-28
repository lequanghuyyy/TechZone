package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.ProductDetailDto;
import com.springboot.shopbubu.repository.ProductDetailRepository;
import com.springboot.shopbubu.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl{
    private final ProductDetailRepository productDetailRepository;
    @Autowired
    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

}

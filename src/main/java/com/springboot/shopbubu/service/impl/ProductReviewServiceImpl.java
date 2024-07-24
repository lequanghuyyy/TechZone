package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.ProductReviewDto;
import com.springboot.shopbubu.repository.ProductReviewRepository;
import com.springboot.shopbubu.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    @Autowired
    public ProductReviewServiceImpl(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }
    @Override
    public ProductReviewDto create(ProductReviewDto productReviewDto) {
        return null;
    }

    @Override
    public ProductReviewDto findById(Long id) {
        return null;
    }

    @Override
    public ProductReviewDto update(ProductReviewDto productReviewDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

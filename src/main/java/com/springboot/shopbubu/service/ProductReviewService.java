package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.ProductReviewDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductReviewService {
    ProductReviewDto create(ProductReviewDto productReviewDto);
    ProductReviewDto findById(Long id);
    ProductReviewDto update(ProductReviewDto productReviewDto);
    void delete(Long id);
}

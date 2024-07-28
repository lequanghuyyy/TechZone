package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.ProductReviewDto;
import com.springboot.shopbubu.entity.ProductReviewEntity;
import com.springboot.shopbubu.mapper.ProductReviewMapper;
import com.springboot.shopbubu.repository.ProductReviewRepository;
import com.springboot.shopbubu.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewMapper productReviewMapper;
    @Autowired
    public ProductReviewServiceImpl(ProductReviewRepository productReviewRepository, ProductReviewMapper productReviewMapper) {
        this.productReviewRepository = productReviewRepository;
        this.productReviewMapper = productReviewMapper;
    }
    @Override
    public ProductReviewDto create(ProductReviewDto productReviewDto) {
        ProductReviewEntity productReviewEntity = productReviewMapper.convertToProductReviewEntity(productReviewDto);
        productReviewEntity = productReviewRepository.save(productReviewEntity);
        productReviewDto.setId(productReviewEntity.getId());
        return productReviewDto;
    }

    @Override
    public ProductReviewDto findById(Long id) {
        return productReviewRepository.findById(id).map(productReviewMapper::convertToProductReviewDto).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public ProductReviewDto update(ProductReviewDto productReviewDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        productReviewRepository.deleteById(id);
    }
}

package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.ProductReviewDto;
import com.springboot.shopbubu.entity.ProductReviewEntity;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public ProductReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public ProductReviewDto convertToProductReviewDto(ProductReviewEntity productReviewEntity) {
        return modelMapper.map(productReviewEntity, ProductReviewDto.class);
    }
    public ProductReviewEntity convertToProductReviewEntity(ProductReviewDto productReviewDto) {
        return modelMapper.map(productReviewDto, ProductReviewEntity.class);
    }
}

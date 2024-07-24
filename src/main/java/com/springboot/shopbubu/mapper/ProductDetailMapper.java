package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.ProductDetailDto;
import com.springboot.shopbubu.entity.ProductDetailEntity;
import com.springboot.shopbubu.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public ProductDetailMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public ProductDetailDto convertToProductDetailDto(ProductDetailEntity productDetailEntity){
        return modelMapper.map(productDetailEntity, ProductDetailDto.class);
    }
    public ProductDetailEntity convertToProductDetailEntity(ProductDetailDto productDetailDto){
        return modelMapper.map(productDetailDto, ProductDetailEntity.class);
    }
}

package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;
    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public ProductDto convertToProductDto(ProductEntity productEntity){
        return modelMapper.map(productEntity, ProductDto.class);
    }
    public ProductEntity convertToProductEntity(ProductDto productDto){
        return modelMapper.map(productDto, ProductEntity.class);
    }
}

package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;
    private final ProductDetailMapper productDetailMapper;
    @Autowired
    public ProductMapper(ModelMapper modelMapper, ProductDetailMapper productDetailMapper) {
        this.modelMapper = modelMapper;
        this.productDetailMapper = productDetailMapper;
    }
    public ProductDto convertToProductDto(ProductEntity productEntity){
        ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
        productDto.setCategoryName(productEntity.getCategory().getCategoryName());
//        productDto.setProductDetail(productDetailMapper.convertToProductDetailDto(productEntity.getProductDetail()));
        return modelMapper.map(productEntity, ProductDto.class);
    }
    public ProductEntity convertToProductEntity(ProductDto productDto){
        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
        return modelMapper.map(productDto, ProductEntity.class);
    }
}

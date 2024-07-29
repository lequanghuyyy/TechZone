package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CategoryDto;
import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.dto.ProductSummaryDto;
import com.springboot.shopbubu.entity.CategoryEntity;
import com.springboot.shopbubu.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;
    @Autowired
    public CategoryMapper(ModelMapper modelMapper, ProductMapper productMapper) {
        this.modelMapper = modelMapper;
        this.productMapper = productMapper;
    }
    public CategoryDto convertToCategoryDto(CategoryEntity categoryEntity) {
        CategoryDto categoryDto = modelMapper.map(categoryEntity, CategoryDto.class);
        List<ProductSummaryDto> productSummaryDto = new ArrayList<>();
        List<ProductEntity> productEntities = categoryEntity.getProduct();
        for (ProductEntity p : productEntities){
            productSummaryDto.add(productMapper.convertToProductSummaryDto(p));
        }
        categoryDto.setProductSummaryDto(productSummaryDto);
        return categoryDto;
    }
    public CategoryEntity convertToCategoryEntity(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = modelMapper.map(categoryDto, CategoryEntity.class);
        if (categoryDto.getCreatedAt() == null){
            categoryEntity.setCreatedAt(new Date());
        }
        if (categoryDto.getUpdatedAt() == null){
            categoryEntity.setUpdatedAt(new Date());
        }
        return categoryEntity;
    }
}

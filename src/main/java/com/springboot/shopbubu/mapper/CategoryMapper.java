package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CategoryDto;
import com.springboot.shopbubu.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public CategoryDto convertToCategoryDto(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, CategoryDto.class);
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

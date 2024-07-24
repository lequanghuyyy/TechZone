package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CategoryDto;
import com.springboot.shopbubu.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return modelMapper.map(categoryDto, CategoryEntity.class);
    }
}

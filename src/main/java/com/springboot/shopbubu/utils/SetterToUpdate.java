package com.springboot.shopbubu.utils;

import com.springboot.shopbubu.dto.CategoryDto;
import com.springboot.shopbubu.entity.CategoryEntity;

import java.util.Date;

public class SetterToUpdate {
    private SetterToUpdate() {};
    public static void setCategory(CategoryDto categoryDto, CategoryEntity categoryEntity) {
        categoryEntity.setUpdatedAt(new Date());
        if (categoryDto.getCategoryName() != null){
            categoryEntity.setCategoryName(categoryDto.getCategoryName());
        }
        if (categoryDto.getDescription() != null){
            categoryEntity.setDescription(categoryDto.getDescription());
        }
        if (categoryDto.getImage() != null){
            categoryEntity.setImage(categoryDto.getImage());
        }
    }

}

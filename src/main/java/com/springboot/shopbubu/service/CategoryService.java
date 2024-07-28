package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
     List<CategoryDto> findAll();
     CategoryDto findByCategoryName(String categoryName);
    CategoryDto create(CategoryDto categoryDto);
    CategoryDto update(CategoryDto categoryDto);
    void delete(Long id);
}

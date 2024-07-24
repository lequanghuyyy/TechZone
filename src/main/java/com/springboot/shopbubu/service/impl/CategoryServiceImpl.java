package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.CategoryDto;
import com.springboot.shopbubu.repository.CategoryRepository;
import com.springboot.shopbubu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<CategoryDto> findAll() {
        return List.of();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

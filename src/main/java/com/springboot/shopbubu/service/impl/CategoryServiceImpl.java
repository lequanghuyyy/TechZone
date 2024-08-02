package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.CategoryDto;
import com.springboot.shopbubu.entity.CategoryEntity;
import com.springboot.shopbubu.exception.AlreadyExistsException;
import com.springboot.shopbubu.mapper.CategoryMapper;
import com.springboot.shopbubu.repository.CategoryRepository;
import com.springboot.shopbubu.service.CategoryService;
import com.springboot.shopbubu.utils.SetterToUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::convertToCategoryDto).toList();
    }

    @Override
    public CategoryDto findByCategoryName(String categoryName) {
        return categoryMapper.convertToCategoryDto(categoryRepository.findByCategoryName(categoryName));
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        if (categoryRepository.findByCategoryName(categoryDto.getCategoryName()) != null) {
            throw new AlreadyExistsException("Category","CategoryName",categoryDto.getCategoryName());
        }
        CategoryEntity categoryEntity = categoryMapper.convertToCategoryEntity(categoryDto);
        categoryRepository.save(categoryEntity);
        categoryDto.setId(categoryEntity.getId());
        return categoryMapper.convertToCategoryDto(categoryEntity);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryDto.getId()).orElseThrow(() -> new NoSuchElementException("Not found category with id :" + categoryDto.getId()));
        SetterToUpdate.setCategory(categoryDto,categoryEntity);
        categoryRepository.save(categoryEntity);
        return categoryMapper.convertToCategoryDto(categoryEntity);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}

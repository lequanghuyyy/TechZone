package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.entity.CategoryEntity;
import com.springboot.shopbubu.entity.ProductEntity;
import com.springboot.shopbubu.mapper.CategoryMapper;
import com.springboot.shopbubu.mapper.ProductDetailMapper;
import com.springboot.shopbubu.mapper.ProductMapper;
import com.springboot.shopbubu.repository.CategoryRepository;
import com.springboot.shopbubu.repository.ProductDetailRepository;
import com.springboot.shopbubu.repository.ProductRepository;
import com.springboot.shopbubu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductMapper productMapper;
    private final ProductDetailMapper productDetailMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDetailRepository productDetailRepository, ProductMapper productMapper, ProductDetailMapper productDetailMapper, CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.productMapper = productMapper;
        this.productDetailMapper = productDetailMapper;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::convertToProductDto).toList();
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        if (categoryRepository.findByCategoryName(productDto.getCategoryName())==null){
            throw new NoSuchElementException("Category not found");
        }
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(productDto.getCategoryName());
         ProductEntity productEntity = productMapper.convertToProductEntity(productDto);
         productEntity.setCategory(categoryEntity);
         productEntity = productRepository.save(productEntity);
         productDto.setId(productEntity.getId());
        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findById(id).map(productMapper::convertToProductDto).orElseThrow(() -> new RuntimeException("Product not found"));
    }
    @Override
    public ProductDto update(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}

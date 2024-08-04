package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.dto.paging.PageDto;
import com.springboot.shopbubu.dto.paging.ProductSearchRequest;
import com.springboot.shopbubu.entity.CategoryEntity;
import com.springboot.shopbubu.entity.ProductDetailEntity;
import com.springboot.shopbubu.entity.ProductEntity;
import com.springboot.shopbubu.exception.notFoundException.NotFoundProductException;
import com.springboot.shopbubu.mapper.CategoryMapper;
import com.springboot.shopbubu.mapper.ProductDetailMapper;
import com.springboot.shopbubu.mapper.ProductMapper;
import com.springboot.shopbubu.repository.CategoryRepository;
import com.springboot.shopbubu.repository.ProductCustomRepository;
import com.springboot.shopbubu.repository.ProductDetailRepository;
import com.springboot.shopbubu.repository.ProductRepository;
import com.springboot.shopbubu.service.ProductService;
import com.springboot.shopbubu.utils.SetterToUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductMapper productMapper;
    private final ProductDetailMapper productDetailMapper;
    private final CategoryRepository categoryRepository;
    private final ProductCustomRepository productCustomRepository;@Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDetailRepository productDetailRepository, ProductMapper productMapper, ProductDetailMapper productDetailMapper, CategoryRepository categoryRepository, ProductCustomRepository productCustomRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.productMapper = productMapper;
        this.productDetailMapper = productDetailMapper;
        this.categoryRepository = categoryRepository;
        this.productCustomRepository = productCustomRepository;
    }

    @Override
    public PageDto<ProductDto> findAll(ProductSearchRequest productSearchRequest) {
        if (productSearchRequest.getKeyword() == null) {
            productSearchRequest.setKeyword(" ");
        }
        List<ProductEntity> productEntities = productCustomRepository.listProducts(
                productSearchRequest.getKeyword(),
                productSearchRequest.getPage(),
                productSearchRequest.getSize(),
                productSearchRequest.getMinPrice(),
                productSearchRequest.getMaxPrice());
        String categoryName = findCategoryNameById(productEntities.getFirst().getCategory().getId());
        List<ProductDto> productDtos = productEntities.stream()
                .map(productEntity -> {
                    ProductDto productDto = productMapper.convertToProductDto(productEntity);
                    productDto.setCategoryName(categoryName);
                    return productDto;
                })
                .collect(Collectors.toList());
        long totalElements = productCustomRepository.countProducts(productSearchRequest.getKeyword(),productSearchRequest.getMinPrice(),productSearchRequest.getMaxPrice());
        long totalPages = (long) Math.ceil((double) totalElements / (double) productSearchRequest.getSize());
        return PageDto.<ProductDto>builder().items(productDtos)
                .size(productSearchRequest.getSize())
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        if (categoryRepository.findByCategoryName(productDto.getCategoryName())==null){
            throw new NoSuchElementException("Category not found");
        }
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(productDto.getCategoryName());
         ProductEntity productEntity = productMapper.convertToProductEntity(productDto);
        ProductDetailEntity productDetailEntity = productDetailMapper.convertToProductDetailEntity(productDto.getProductDetail());
         productEntity.setCategory(categoryEntity);
        productDetailEntity.setProduct(productEntity);
        productEntity.setProductDetail(productDetailEntity);
         productEntity = productRepository.save(productEntity);
         productDto = productMapper.convertToProductDto(productEntity);
         productDto.setCategoryName(categoryEntity.getCategoryName());
        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findById(id).map(productMapper::convertToProductDto).orElseThrow(() -> new NotFoundProductException("Product not found"));
    }
    @Override
    public ProductDto update(ProductDto productDto) {
        ProductEntity productEntity = productRepository.findById(productDto.getId()).orElseThrow(() -> new NotFoundProductException("Product not found"));
        SetterToUpdate.setProduct(productEntity, productDto);
        productEntity = productRepository.save(productEntity);
        return productMapper.convertToProductDto(productEntity);
    }
    @Override
    public void deleteById(Long id) {
        productDetailRepository.deleteById(id);
        productRepository.deleteById(id);
    }
    public String findCategoryNameById(Long id) {
        return categoryRepository.findById(id).get().getCategoryName();
    }
}

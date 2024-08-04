package com.springboot.shopbubu.repository;

import com.springboot.shopbubu.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductCustomRepository {
    List<ProductEntity> listProducts(String keySearch,int page, int pageSize, BigDecimal minPrice, BigDecimal maxPrice);
    long countProducts(String keySearch, BigDecimal minPrice, BigDecimal maxPrice);
}

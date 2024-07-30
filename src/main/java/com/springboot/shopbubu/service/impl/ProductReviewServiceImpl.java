package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.ProductReviewDto;
import com.springboot.shopbubu.entity.CustomerDetailEntity;
import com.springboot.shopbubu.entity.ProductDetailEntity;
import com.springboot.shopbubu.entity.ProductReviewEntity;
import com.springboot.shopbubu.mapper.ProductReviewMapper;
import com.springboot.shopbubu.repository.CustomerDetailRepository;
import com.springboot.shopbubu.repository.ProductDetailRepository;
import com.springboot.shopbubu.repository.ProductReviewRepository;
import com.springboot.shopbubu.security.CustomUserDetails;
import com.springboot.shopbubu.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewMapper productReviewMapper;
    private final CustomerDetailRepository customerDetailRepository;
    private final ProductDetailRepository productDetailRepository;
    @Autowired
    public ProductReviewServiceImpl(ProductReviewRepository productReviewRepository, ProductReviewMapper productReviewMapper, CustomerDetailRepository customerDetailRepository, ProductDetailRepository productDetailRepository) {
        this.productReviewRepository = productReviewRepository;
        this.productReviewMapper = productReviewMapper;
        this.customerDetailRepository = customerDetailRepository;
        this.productDetailRepository = productDetailRepository;
    }
    @Override
    public ProductReviewDto create(ProductReviewDto productReviewDto) {
        productReviewDto.setCustomerDetailId(getIdUserCurrent());
        Optional<ProductDetailEntity> productDetailEntity = productDetailRepository.findById(Long.valueOf(productReviewDto.getProductDetailId()));
        Optional<CustomerDetailEntity> customerDetailEntity = customerDetailRepository.findById(Long.valueOf(productReviewDto.getCustomerDetailId()));
        ProductReviewEntity productReviewEntity = productReviewMapper.convertToProductReviewEntity(productReviewDto);
        if (productDetailEntity.isPresent() && customerDetailEntity.isPresent()){
            productReviewEntity.setProductDetail(productDetailEntity.get());
            productReviewEntity.setCustomerDetail(customerDetailEntity.get());
        }
        productReviewEntity = productReviewRepository.save(productReviewEntity);
        productReviewDto.setId(productReviewEntity.getId());
        return productReviewMapper.convertToProductReviewDto(productReviewEntity);
    }

    @Override
    public ProductReviewDto findById(Long id) {
        return productReviewRepository.findById(id).map(productReviewMapper::convertToProductReviewDto).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public ProductReviewDto update(ProductReviewDto productReviewDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        productReviewRepository.deleteById(id);
    }
    public Integer getIdUserCurrent(){
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Math.toIntExact(principal.getUser().getId());
    }

}

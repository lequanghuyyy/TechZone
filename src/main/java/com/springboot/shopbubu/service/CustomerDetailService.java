package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.CustomerDetailDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomerDetailService {
    CustomerDetailDto create(CustomerDetailDto customerDetailDto);
    CustomerDetailDto update(CustomerDetailDto customerDetailDto);
    CustomerDetailDto findById(Long id);
    CustomerDetailDto findByName(String name);
    void delete(Long id);
}

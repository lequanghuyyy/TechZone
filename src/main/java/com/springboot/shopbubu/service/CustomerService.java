package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.CustomerDto;
import com.springboot.shopbubu.dto.CustomerSummaryDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
    List<CustomerSummaryDto> findAll();
    CustomerDto create(CustomerDto customerDto);
    CustomerDto findById(Long id);
    CustomerDto findByName(String name);
    CustomerDto update(CustomerDto customerDto);
    void deleteById(Long id);
}

package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> findAll();
    CustomerDto findById(Long id);
    CustomerDto findByName(String name);
    CustomerDto create(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto);
    void deleteById(Long id);
}

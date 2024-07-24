package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.CustomerDto;
import com.springboot.shopbubu.repository.CustomerRepository;
import com.springboot.shopbubu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> findAll() {
        return List.of();
    }

    @Override
    public CustomerDto findById(Long id) {
        return null;
    }

    @Override
    public CustomerDto findByName(String name) {
        return null;
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        return null;
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

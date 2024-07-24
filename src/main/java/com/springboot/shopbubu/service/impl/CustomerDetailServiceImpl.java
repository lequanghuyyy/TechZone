package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.CustomerDetailDto;
import com.springboot.shopbubu.repository.CustomerDetailRepository;
import com.springboot.shopbubu.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {
    private final CustomerDetailRepository customerDetailRepository;
    @Autowired
    public CustomerDetailServiceImpl(CustomerDetailRepository customerDetailRepository) {
        this.customerDetailRepository = customerDetailRepository;
    }
    @Override
    public CustomerDetailDto create(CustomerDetailDto customerDetailDto) {
        return null;
    }

    @Override
    public CustomerDetailDto update(CustomerDetailDto customerDetailDto) {
        return null;
    }

    @Override
    public CustomerDetailDto findById(Long id) {
        return null;
    }

    @Override
    public CustomerDetailDto findByName(String name) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

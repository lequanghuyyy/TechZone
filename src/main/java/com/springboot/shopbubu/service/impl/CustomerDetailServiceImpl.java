package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.CustomerDetailDto;
import com.springboot.shopbubu.mapper.CustomerDetailMapper;
import com.springboot.shopbubu.repository.CustomerDetailRepository;
import com.springboot.shopbubu.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {
    private final CustomerDetailRepository customerDetailRepository;
    private final CustomerDetailMapper customerDetailMapper;
    @Autowired
    public CustomerDetailServiceImpl(CustomerDetailRepository customerDetailRepository, CustomerDetailMapper customerDetailMapper) {
        this.customerDetailRepository = customerDetailRepository;
        this.customerDetailMapper = customerDetailMapper;
    }

    @Override
    public CustomerDetailDto update(CustomerDetailDto customerDetailDto) {
        return null;
    }

    @Override
    public CustomerDetailDto findById(Long id) {
        return customerDetailRepository.findById(id).map(customerDetailMapper::convertToCustomerDetailDto).orElseThrow(NoSuchElementException::new);
    }

}

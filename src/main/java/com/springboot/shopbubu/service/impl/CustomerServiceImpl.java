package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.CustomerDto;


import com.springboot.shopbubu.entity.CustomerEntity;
import com.springboot.shopbubu.mapper.CustomerDetailMapper;
import com.springboot.shopbubu.mapper.CustomerMapper;
import com.springboot.shopbubu.repository.CustomerDetailRepository;
import com.springboot.shopbubu.repository.CustomerRepository;
import com.springboot.shopbubu.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDetailRepository customerDetailRepository;
    private final CustomerMapper customerMapper;
    private final CustomerDetailMapper customerDetailMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDetailRepository customerDetailRepository, CustomerMapper customerMapper, CustomerDetailMapper customerDetailMapper) {
        this.customerRepository = customerRepository;
        this.customerDetailRepository = customerDetailRepository;
        this.customerMapper = customerMapper;
        this.customerDetailMapper = customerDetailMapper;
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream().map(customerMapper::convertToCustomerDto).toList();
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
       CustomerEntity customerEntity = customerMapper.convertToCustomerEntity(customerDto);
        customerEntity = customerRepository.save(customerEntity);
        customerDetailRepository.save(customerDetailMapper.convertToCustomerDetailEntity(customerDto.getCustomerDetail()));
        return customerMapper.convertToCustomerDto(customerEntity);
    }

    @Override
    public CustomerDto findById(Long id) {
       return customerRepository.findById(id).map(customerMapper::convertToCustomerDto).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public CustomerDto findByName(String name) {
        CustomerEntity customerEntity = customerRepository.findByName(name);
        if (customerEntity == null) {
            throw new NoSuchElementException("Not Found Customer have name :" + name);
        }
        return customerMapper.convertToCustomerDto(customerEntity);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        if (customerRepository.findById(customerDto.getId()).isEmpty()) {
            throw new NoSuchElementException("Customer not found");
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

}

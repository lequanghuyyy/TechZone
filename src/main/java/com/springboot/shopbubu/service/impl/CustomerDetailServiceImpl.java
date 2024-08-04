package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.CustomerDetailDto;
import com.springboot.shopbubu.entity.CustomerDetailEntity;
import com.springboot.shopbubu.entity.CustomerEntity;
import com.springboot.shopbubu.exception.notFoundException.NotFoundCustomerException;
import com.springboot.shopbubu.mapper.CustomerDetailMapper;
import com.springboot.shopbubu.repository.CustomerDetailRepository;
import com.springboot.shopbubu.repository.CustomerRepository;
import com.springboot.shopbubu.repository.UserRepository;
import com.springboot.shopbubu.security.CustomUserDetails;
import com.springboot.shopbubu.service.CustomerDetailService;
import com.springboot.shopbubu.utils.SetterToUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {
    private final CustomerDetailRepository customerDetailRepository;
    private final CustomerDetailMapper customerDetailMapper;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    @Autowired
    public CustomerDetailServiceImpl(CustomerDetailRepository customerDetailRepository, CustomerDetailMapper customerDetailMapper, CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerDetailRepository = customerDetailRepository;
        this.customerDetailMapper = customerDetailMapper;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CustomerDetailDto update(CustomerDetailDto customerDetailDto) {
        CustomerDetailEntity customerDetailEntity = customerDetailRepository.findById(getIdCustomer())
                .orElseThrow(() -> new NotFoundCustomerException("Customer not found with id: " + getIdCustomer()));
        SetterToUpdate.setCustomerDetail(customerDetailEntity, customerDetailDto);
        customerDetailRepository.save(customerDetailEntity);
        return customerDetailMapper.convertToCustomerDetailDto(customerDetailEntity);
    }

    @Override
    public CustomerDetailDto findById(Long id) {
        return customerDetailRepository.findById(id).map(customerDetailMapper::convertToCustomerDetailDto).orElseThrow(() -> new NotFoundCustomerException("Customer not found with id: " + getIdCustomer()));

    }
    public Long getIdCustomer(){
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerEntity customerEntity = principal.getUser().getCustomerEntity();
        return customerEntity.getId();
    }
}

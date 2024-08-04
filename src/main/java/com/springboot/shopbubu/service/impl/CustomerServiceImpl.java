package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.CustomerDto;


import com.springboot.shopbubu.dto.CustomerSummaryDto;
import com.springboot.shopbubu.entity.CustomerDetailEntity;
import com.springboot.shopbubu.entity.CustomerEntity;
import com.springboot.shopbubu.entity.UserEntity;
import com.springboot.shopbubu.exception.notFoundException.NotFoundCustomerException;
import com.springboot.shopbubu.mapper.CustomerDetailMapper;
import com.springboot.shopbubu.mapper.CustomerMapper;
import com.springboot.shopbubu.repository.CustomerDetailRepository;
import com.springboot.shopbubu.repository.CustomerRepository;
import com.springboot.shopbubu.repository.UserRepository;
import com.springboot.shopbubu.security.CustomUserDetails;
import com.springboot.shopbubu.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerDetailMapper customerDetailMapper;
    private final UserRepository userRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerDetailMapper customerDetailMapper, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerDetailMapper = customerDetailMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<CustomerSummaryDto> findAll() {
        return customerRepository.findAll().stream().map(customerMapper::covertToCustomerSummaryDto).toList();
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        customerDto.getCustomerDetail().setAge(calculateAge(customerDto.getCustomerDetail().getBirthday()));
        CustomerEntity customerEntity = customerMapper.convertToCustomerEntity(customerDto);
        UserEntity userEntity = findUserEntityById(getIdUserCurrent());
        customerEntity.setUser(userEntity);
        CustomerDetailEntity customerDetailEntity = customerDetailMapper.convertToCustomerDetailEntity(customerDto.getCustomerDetail());
        customerEntity.setCustomerDetail(customerDetailEntity);
        customerDetailEntity.setCustomer(customerEntity);
        customerEntity = customerRepository.save(customerEntity);
        return customerMapper.convertToCustomerDto(customerEntity);
    }

    @Override
    public CustomerDto findById(Long id) {
       return customerRepository.findById(id).map(customerMapper::convertToCustomerDto)
               .orElseThrow();
    }

    @Override
    public CustomerDto findByName(String name) {
        CustomerEntity customerEntity = customerRepository.findByName(name);
        if (customerEntity == null) {
            throw new NotFoundCustomerException("Customer is not existed");
        }
        return customerMapper.convertToCustomerDto(customerEntity);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public int calculateAge(Date birthDate) {
        LocalDate birthLocalDate = birthDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthLocalDate, currentDate).getYears();
    }
    public Long getIdUserCurrent(){
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser().getId();
    }
    public UserEntity findUserEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundCustomerException("Not found user"));
    }

}

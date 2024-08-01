package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CustomerDto;
import com.springboot.shopbubu.dto.CustomerSummaryDto;
import com.springboot.shopbubu.entity.CustomerDetailEntity;
import com.springboot.shopbubu.entity.CustomerEntity;
import com.springboot.shopbubu.entity.UserEntity;
import com.springboot.shopbubu.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class CustomerMapper {
    private final ModelMapper modelMapper;
    private final CustomerDetailMapper customerDetailMapper;
    @Autowired
    public CustomerMapper(ModelMapper modelMapper, CustomerDetailMapper customerDetailMapper) {
        this.modelMapper = modelMapper;
        this.customerDetailMapper = customerDetailMapper;
    }
    public CustomerEntity convertToCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        customerEntity.setCustomerDetail(customerDetailMapper.convertToCustomerDetailEntity(customerDto.getCustomerDetail()));

        return customerEntity;
    }
    public CustomerDto convertToCustomerDto(CustomerEntity customerEntity) {
        Long userId = customerEntity.getUser().getId();
        CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);
        customerDto.setUserId(userId);
        return customerDto;
    }
    public CustomerSummaryDto covertToCustomerSummaryDto(CustomerEntity customerEntity) {
        CustomerSummaryDto customerSummaryDto = modelMapper.map(customerEntity, CustomerSummaryDto.class);
        customerSummaryDto.setUserId(customerEntity.getUser().getId());
        return customerSummaryDto;
    }
}

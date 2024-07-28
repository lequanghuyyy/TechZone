package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CustomerDto;
import com.springboot.shopbubu.entity.CustomerDetailEntity;
import com.springboot.shopbubu.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return modelMapper.map(customerDto, CustomerEntity.class);
    }
    public CustomerDto convertToCustomerDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }
}

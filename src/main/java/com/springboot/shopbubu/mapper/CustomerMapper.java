package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CustomerDto;
import com.springboot.shopbubu.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public CustomerDto convertToCustomerEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, CustomerDto.class);
    }
    public CustomerDto convertToCustomerDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }
}

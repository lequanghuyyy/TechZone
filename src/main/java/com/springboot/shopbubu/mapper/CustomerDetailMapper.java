package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CustomerDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public CustomerDetailMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public CustomerDetailDto convertToCustomerDetailDto(CustomerDetailMapper customerDetailMapper) {
        return this.modelMapper.map(customerDetailMapper, CustomerDetailDto.class);
    }
    public CustomerDetailMapper convertToCustomerDetailEntity(CustomerDetailDto customerDetailDto) {
        return this.modelMapper.map(customerDetailDto, CustomerDetailMapper.class);
    }
}

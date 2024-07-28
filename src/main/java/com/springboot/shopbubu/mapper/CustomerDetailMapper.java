package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CustomerDetailDto;
import com.springboot.shopbubu.entity.CustomerDetailEntity;
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
    public CustomerDetailDto convertToCustomerDetailDto(CustomerDetailEntity customerDetailEntity) {
        return this.modelMapper.map(customerDetailEntity, CustomerDetailDto.class);
    }
    public CustomerDetailEntity convertToCustomerDetailEntity(CustomerDetailDto customerDetailDto) {
        return this.modelMapper.map(customerDetailDto, CustomerDetailEntity.class);
    }
}

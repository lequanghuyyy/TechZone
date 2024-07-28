package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.OrderDetailDto;
import com.springboot.shopbubu.entity.OrderDetailEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public OrderDetailMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public OrderDetailDto convertToOrderDetailDto(OrderDetailEntity orderDetailEntity) {
        return modelMapper.map(orderDetailEntity, OrderDetailDto.class);
    }
    public OrderDetailEntity convertToOrderDetailEntity(OrderDetailDto orderDetailDto) {
        return modelMapper.map(orderDetailDto, OrderDetailEntity.class);
    }
}

package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.OrderDetailDto;
import com.springboot.shopbubu.dto.OrderDto;
import com.springboot.shopbubu.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public OrderDto convertToOrderDto(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDto.class);
    }
    public OrderEntity convertToOrderEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, OrderEntity.class);
    }
}


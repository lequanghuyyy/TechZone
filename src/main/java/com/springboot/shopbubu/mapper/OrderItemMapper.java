package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.OrderItemDto;
import com.springboot.shopbubu.entity.OrderItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public OrderItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public OrderItemDto convertToOrderItemDto(OrderItemEntity orderItemEntity) {
        return modelMapper.map(orderItemEntity, OrderItemDto.class);
    }
    public OrderItemEntity convertToOrderItemEntity(OrderItemDto orderItemDto) {
        return modelMapper.map(orderItemDto, OrderItemEntity.class);
    }
}

package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CartProductDto;
import com.springboot.shopbubu.dto.OrderItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartProductMapOrderItem {
    private final ModelMapper modelMapper;
    @Autowired
    public CartProductMapOrderItem(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public OrderItemDto cartProductMapOrderItem(CartProductDto cartProductDto) {
        OrderItemDto orderItemDto = modelMapper.map(cartProductDto, OrderItemDto.class);
        return orderItemDto;
    }
}

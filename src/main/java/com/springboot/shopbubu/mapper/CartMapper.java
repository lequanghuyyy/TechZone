package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CartDto;
import com.springboot.shopbubu.entity.CartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public CartMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public CartDto convertToCartDto(CartEntity cartEntity) {
        CartDto cartDto = modelMapper.map(cartEntity, CartDto.class);
        return cartDto;
    }
    public CartEntity convertToCartEntity(CartDto cartDto) {
        CartEntity cartEntity = modelMapper.map(cartDto, CartEntity.class);
        return cartEntity;
    }
}

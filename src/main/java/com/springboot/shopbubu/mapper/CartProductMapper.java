package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CartProductDto;
import com.springboot.shopbubu.entity.CartEntity;
import com.springboot.shopbubu.entity.CartProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartProductMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public CartProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public CartProductDto convertToCartDto(CartEntity cartEntity){
        return modelMapper.map(cartEntity, CartProductDto.class);
    }
    public CartProductEntity convertToCartProductEntity(CartProductDto cartProductDto){
        return modelMapper.map(cartProductDto, CartProductEntity.class);
    }
}

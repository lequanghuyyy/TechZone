package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CartDto;
import com.springboot.shopbubu.dto.CartProductDto;
import com.springboot.shopbubu.entity.CartEntity;
import com.springboot.shopbubu.entity.CartProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {
    private final ModelMapper modelMapper;
    private final CartProductMapper cartProductMapper;
    @Autowired
    public CartMapper(ModelMapper modelMapper, CartProductMapper cartProductMapper) {
        this.modelMapper = modelMapper;
        this.cartProductMapper = cartProductMapper;
    }
    public CartDto convertToCartDto(CartEntity cartEntity) {
        CartDto cartDto = modelMapper.map(cartEntity, CartDto.class);
        cartDto.setCustomerId(cartEntity.getCustomer().getId());
        cartDto.setCartProducts(cartProductMapper.convertListCartProductDto(cartEntity.getCartProducts()));
        return cartDto;
    }
    public CartEntity convertToCartEntity(CartDto cartDto) {
        CartEntity cartEntity = modelMapper.map(cartDto, CartEntity.class);
        return cartEntity;
    }
}

package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.CartProductDto;
import com.springboot.shopbubu.entity.CartEntity;
import com.springboot.shopbubu.entity.CartProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartProductMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public CartProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public CartProductDto convertToCartDto(CartProductEntity cartProductEntity){
        CartProductDto cartProductDto = modelMapper.map(cartProductEntity, CartProductDto.class);
        cartProductDto.setProductId(Math.toIntExact(cartProductEntity.getProduct().getId()));
        return cartProductDto;
    }
    public CartProductEntity convertToCartProductEntity(CartProductDto cartProductDto){
        return modelMapper.map(cartProductDto, CartProductEntity.class);
    }
    public List<CartProductDto> convertListCartProductDto(List<CartProductEntity> cartProductEntities){
        List<CartProductDto> cartProductDtoList = new ArrayList<>();
        for (CartProductEntity cartProductEntity : cartProductEntities){
            cartProductDtoList.add(convertToCartDto(cartProductEntity));
        }
        return cartProductDtoList;
    }
}

package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.CartDto;
import com.springboot.shopbubu.dto.CartProductDto;
import com.springboot.shopbubu.entity.CartEntity;
import com.springboot.shopbubu.entity.CartProductEntity;
import com.springboot.shopbubu.mapper.CartMapper;
import com.springboot.shopbubu.mapper.CartProductMapper;
import com.springboot.shopbubu.mapper.CustomerMapper;
import com.springboot.shopbubu.repository.CartProductRepository;
import com.springboot.shopbubu.repository.CartRepository;
import com.springboot.shopbubu.repository.CustomerRepository;
import com.springboot.shopbubu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final CartMapper cartMapper;
    private final CartProductMapper cartProductMapper;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartProductRepository cartProductRepository, CartMapper cartMapper, CartProductMapper cartProductMapper, CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.cartMapper = cartMapper;
        this.cartProductMapper = cartProductMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CartDto> findALl() {
        return cartRepository.findAll().stream().map(cartMapper::convertToCartDto).toList();
    }

    @Override
    public CartDto findById(Long id) {
        return cartRepository.findById(id).map(cartMapper::convertToCartDto).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public CartDto create(CartDto cartDto) {
        CartEntity cartEntity = cartMapper.convertToCartEntity(cartDto);
//        cartEntity.setCustomer(customerMapper.convertToCustomerEntity(cartDto.getCustomerId()));
        cartRepository.save(cartEntity);
        return cartMapper.convertToCartDto(cartEntity);
    }

    @Override
    public CartDto update(CartDto cartDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}

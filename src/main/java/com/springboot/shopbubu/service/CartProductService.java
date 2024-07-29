package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.CartProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartProductService {
    CartProductDto create(CartProductDto cartProductDto);
    List<CartProductDto> findAll();
    CartProductDto update(CartProductDto cartProductDto);
    CartProductDto delete(Long id);
}

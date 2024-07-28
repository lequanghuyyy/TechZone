package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<CartDto> findALl();
    CartDto findById(Long id);
    CartDto create(CartDto cartDto);
    CartDto update(CartDto cartDto);
    void deleteById(Long id);
}

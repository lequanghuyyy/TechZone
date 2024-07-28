package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderDto> findAll();
    OrderDto create(OrderDto orderDto);
    OrderDto findById(Long id);
    OrderDto update(OrderDto orderDto);
    void delete(Long id);
}

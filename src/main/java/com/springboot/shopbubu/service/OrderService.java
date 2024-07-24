package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAllOrders();
    OrderDto findById(Long id);
    OrderDto create(OrderDto orderDto);
    OrderDto update(OrderDto orderDto);
    void delete(Long id);
}

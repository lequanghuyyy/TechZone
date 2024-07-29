package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.OrderItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {
    OrderItemDto create(OrderItemDto orderItemDto);
    OrderItemDto update(OrderItemDto orderItemDto);
    List<OrderItemDto> findAll();
    void delete(Long id);
}

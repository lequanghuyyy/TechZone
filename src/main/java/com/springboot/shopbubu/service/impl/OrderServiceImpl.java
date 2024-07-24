package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.OrderDto;
import com.springboot.shopbubu.repository.OrderRepository;
import com.springboot.shopbubu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> findAllOrders() {
        return List.of();
    }

    @Override
    public OrderDto findById(Long id) {
        return null;
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

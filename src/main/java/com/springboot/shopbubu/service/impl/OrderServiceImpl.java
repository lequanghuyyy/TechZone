package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.OrderDto;
import com.springboot.shopbubu.entity.OrderEntity;
import com.springboot.shopbubu.mapper.OrderDetailMapper;
import com.springboot.shopbubu.mapper.OrderMapper;
import com.springboot.shopbubu.repository.OrderDetailRepository;
import com.springboot.shopbubu.repository.OrderRepository;
import com.springboot.shopbubu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService  {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, OrderDetailMapper orderDetailMapper, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::convertToOrderDto).toList();
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        OrderEntity orderEntity = orderMapper.convertToOrderEntity(orderDto);
        orderEntity = orderRepository.save(orderEntity);
        orderDetailRepository.save(orderDetailMapper.convertToOrderDetailEntity(orderDto.getOrderDetails()));
        return orderMapper.convertToOrderDto(orderEntity);
    }

    @Override
    public OrderDto findById(Long id) {
        return orderRepository
                .findById(id)
                .map(orderMapper::convertToOrderDto)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}

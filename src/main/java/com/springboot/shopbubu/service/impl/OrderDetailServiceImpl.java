package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.OrderDetailDto;
import com.springboot.shopbubu.mapper.OrderDetailMapper;
import com.springboot.shopbubu.repository.OrderDetailRepository;
import com.springboot.shopbubu.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public OrderDetailDto update(OrderDetailDto orderDetailDto) {
        return null;
    }

    @Override
    public OrderDetailDto findById(Long id) {
        return orderDetailRepository.findById(id).map(orderDetailMapper::convertToOrderDetailDto).orElseThrow(NoSuchElementException::new);
    }
}

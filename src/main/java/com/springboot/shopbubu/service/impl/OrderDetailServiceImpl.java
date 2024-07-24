package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.OrderDetailDto;
import com.springboot.shopbubu.repository.OrderDetailRepository;
import com.springboot.shopbubu.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetailDto create(OrderDetailDto orderDetailDto) {
        return null;
    }

    @Override
    public OrderDetailDto update(OrderDetailDto orderDetailDto) {
        return null;
    }

    @Override
    public OrderDetailDto findById(Long id) {
        return null;
    }
}

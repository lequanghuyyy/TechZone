package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.OrderDto;
import com.springboot.shopbubu.dto.request.PaymentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderDto> findAll();
    OrderDto create(PaymentRequest paymentRequest);
    OrderDto findById(Long id);
    OrderDto update(OrderDto orderDto);
    void delete(Long id);
}

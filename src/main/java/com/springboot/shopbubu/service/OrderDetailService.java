package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto create (OrderDetailDto orderDetailDto);
    OrderDetailDto update(OrderDetailDto orderDetailDto);
    OrderDetailDto findById(Long id);
//    List<OrderDetailDto> findByCus();
}

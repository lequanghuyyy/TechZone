package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.OrderDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderDetailService {
    OrderDetailDto update(OrderDetailDto orderDetailDto);
    OrderDetailDto findById(Long id);
//    List<OrderDetailDto> findByCus();
}

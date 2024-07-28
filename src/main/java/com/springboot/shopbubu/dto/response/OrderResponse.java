package com.springboot.shopbubu.dto.response;

import com.springboot.shopbubu.dto.OrderDetailDto;
import com.springboot.shopbubu.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private OrderDetailDto orderDetail;
    private OrderDto orderDto;
}

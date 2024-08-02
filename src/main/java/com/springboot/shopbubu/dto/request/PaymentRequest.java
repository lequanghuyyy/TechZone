package com.springboot.shopbubu.dto.request;

import com.springboot.shopbubu.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class PaymentRequest {
    private Long cartId;
    private OrderDto orderDto;
}

package com.springboot.shopbubu.dto;

import com.springboot.shopbubu.constant.PaymentType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor

public class OrderDetailDto extends AbstractDto {
    private List<OrderItemDto> orderItems;
    private int quantity;
    private Date dateAllocated;
    private PaymentType paymentType;
}

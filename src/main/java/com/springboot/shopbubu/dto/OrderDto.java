package com.springboot.shopbubu.dto;


import com.springboot.shopbubu.utils.OrderStatus;
import com.springboot.shopbubu.utils.PaymentType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class OrderDto {
    private Long id;
    private CustomerDto customer;
    private Date orderDate;
    private Date shippedDate;
    private String shipName;
    private String shipAddress1;
    private String shipCity;
    private BigDecimal shippingFee;
    private PaymentType paymentType;
    private OrderStatus orderStatus;
    private BigDecimal sumPrice;
    private List<OrderDetailDto> orderDetails;
}

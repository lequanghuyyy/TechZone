package com.springboot.shopbubu.dto;

import com.springboot.shopbubu.utils.PaymentType;
import lombok.*;

import java.util.Date;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor

public class OrderDetailDto extends AbstractDto {
    private ProductDto productDto;
    private int quantity;
    private Date dateAllocated;
    private PaymentType paymentType;
}

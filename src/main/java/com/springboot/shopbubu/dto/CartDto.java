package com.springboot.shopbubu.dto;

import com.springboot.shopbubu.utils.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class CartDto extends AbstractDto {

    private CustomerDto customer;
    private BigDecimal totalProduct;
    private CartStatus cartStatus;
    private List<CartProductDto> cartProducts;

}

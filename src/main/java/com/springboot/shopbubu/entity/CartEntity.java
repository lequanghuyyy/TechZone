package com.springboot.shopbubu.entity;

import com.springboot.shopbubu.constant.CartStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carts")
public class CartEntity extends AbstractEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private CustomerEntity customer;

    @Column(name = "total_product", precision = 10, scale = 2)
    private BigDecimal totalProduct;

    @Column(name = "cart_status", length = 255)
    private CartStatus cartStatus;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CartProductEntity> cartProducts;

}

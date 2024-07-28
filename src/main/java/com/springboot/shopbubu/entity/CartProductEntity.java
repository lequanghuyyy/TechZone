package com.springboot.shopbubu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Table(name = "cart_products")
public class CartProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id",nullable = false)
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private ProductEntity product;

    @Column(name = "cart_price", precision = 10, scale = 2)
    private BigDecimal cartPrice;

    @Column(name = "quantity")
    private Integer quantity;
}

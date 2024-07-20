package com.springboot.shopbubu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductEntity extends AbstractEntity<Long> {

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "image")
    private String image;

    @Column(name = "number_purchase")
    private Integer numberPurchase;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToOne(mappedBy = "product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductDetailEntity productDetail;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails;
}

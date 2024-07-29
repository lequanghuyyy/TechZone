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
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private CategoryEntity category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProductDetailEntity productDetail;

    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> orderItem;

    @OneToMany(mappedBy = "product")
    private List<CartProductEntity> cartProducts;
}

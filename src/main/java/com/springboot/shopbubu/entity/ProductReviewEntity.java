package com.springboot.shopbubu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product_reviews")
public class ProductReviewEntity extends AbstractEntity<Long>{

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductDetailEntity productDetail;

    @ManyToOne
    @JoinColumn(name = "customer_detail_id")
    private CustomerDetailEntity customerDetail;
}

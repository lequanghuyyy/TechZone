package com.springboot.shopbubu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_details")
public class ProductDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Column(name = "is_new")
    private Boolean isNew;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

//    @OneToMany(mappedBy = "productDetail")
//    private List<ProductDetailEntity> productDetails;


}

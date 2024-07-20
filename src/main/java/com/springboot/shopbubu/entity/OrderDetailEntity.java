package com.springboot.shopbubu.entity;

import com.springboot.shopbubu.utils.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "order_details")
public class OrderDetailEntity extends AbstractEntity<Long>{

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "date_allocated")
    private Date dateAllocated;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;


}

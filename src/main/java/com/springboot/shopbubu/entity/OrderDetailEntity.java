package com.springboot.shopbubu.entity;

import com.springboot.shopbubu.constant.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "order_details")
public class OrderDetailEntity extends AbstractEntity<Long>{

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "date_allocated")
    private Date dateAllocated;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

}

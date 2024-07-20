package com.springboot.shopbubu.entity;

import com.springboot.shopbubu.utils.OrderStatus;
import com.springboot.shopbubu.utils.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "shipped_date")
    private Date shippedDate;

    @Column(name = "ship_name", nullable = false)
    private String shipName;

    @Column(name = "ship_address1", nullable = false)
    private String shipAddress1;

    @Column(name = "ship_city", nullable = false)
    private String shipCity;

    @Column(name = "shipping_fee", nullable = false, precision = 19, scale = 4)
    private BigDecimal shippingFee;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "sum_price", nullable = false, precision = 19, scale = 4)
    private BigDecimal sumPrice;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails;
}

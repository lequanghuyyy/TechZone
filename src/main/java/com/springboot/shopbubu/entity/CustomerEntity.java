package com.springboot.shopbubu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class CustomerEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @OneToOne(mappedBy = "customer",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerDetailEntity customerDetail;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "used_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> order;
}

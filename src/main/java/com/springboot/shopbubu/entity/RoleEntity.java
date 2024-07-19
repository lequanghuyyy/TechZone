package com.springboot.shopbubu.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

}

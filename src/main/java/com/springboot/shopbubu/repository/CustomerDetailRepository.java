package com.springboot.shopbubu.repository;

import com.springboot.shopbubu.entity.CustomerDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetailEntity,Long> {
}

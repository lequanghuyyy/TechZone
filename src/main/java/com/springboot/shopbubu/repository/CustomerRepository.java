package com.springboot.shopbubu.repository;

import com.springboot.shopbubu.entity.CustomerEntity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

}

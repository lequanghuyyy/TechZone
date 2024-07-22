package com.springboot.shopbubu.repository;

import com.springboot.shopbubu.entity.CategoryEntity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> getAllByCategoryName(String name);
}

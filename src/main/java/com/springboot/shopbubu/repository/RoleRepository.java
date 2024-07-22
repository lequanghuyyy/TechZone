package com.springboot.shopbubu.repository;

import com.springboot.shopbubu.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Set<RoleEntity> findAllByRoleNameIn(List<String> roleNames);
}

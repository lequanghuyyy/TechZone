package com.springboot.shopbubu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends AbstractEntity<Long>{

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> product;

    public CategoryEntity(Long id) {
        this.setId(id);
    }


}

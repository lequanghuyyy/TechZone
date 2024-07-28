package com.springboot.shopbubu.dto;

import com.springboot.shopbubu.entity.ProductEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto extends AbstractDto {
    private String categoryName;
    private String description;
    private String image;
    private List<ProductDto> product;
}

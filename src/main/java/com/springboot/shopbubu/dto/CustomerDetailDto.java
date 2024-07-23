package com.springboot.shopbubu.dto;

import com.springboot.shopbubu.entity.ProductReviewEntity;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class CustomerDetailDto extends AbstractDto {
    private String description;
    private Integer age;
    private String email;
    private Date birthday;
    private String avatar;
    private String phone;
    private String address;
    private String city;
    private CustomerDto customer;
    private List<ProductReviewEntity> productReviewEntities;
}

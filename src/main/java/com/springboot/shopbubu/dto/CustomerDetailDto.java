package com.springboot.shopbubu.dto;


import lombok.*;

import java.util.Date;


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
//    private CustomerDto customer;
//    private List<ProductReviewEntity> productReviewEntities;
}

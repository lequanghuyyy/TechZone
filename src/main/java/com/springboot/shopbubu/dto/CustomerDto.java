package com.springboot.shopbubu.dto;


import lombok.*;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String gender;
    private CustomerDetailDto customerDetail;
    private Long userId;
}

package com.springboot.shopbubu.dto;


import lombok.*;

import java.util.List;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String gender;
    private CustomerDetailDto customerDetail;
    private UserDto userDto;
//    private List<OrderDto> orderDtos;

}

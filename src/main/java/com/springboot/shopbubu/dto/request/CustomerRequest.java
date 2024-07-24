package com.springboot.shopbubu.dto.request;

import com.springboot.shopbubu.dto.CustomerDetailDto;
import com.springboot.shopbubu.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private CustomerDto customerDto;
    private CustomerDetailDto customerDetailDto;
}

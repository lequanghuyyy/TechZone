package com.springboot.shopbubu.dto.response;

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
public class CustomerResponse {
    private CustomerDto customerDto;
    private CustomerDetailDto customerDetailDto;
}

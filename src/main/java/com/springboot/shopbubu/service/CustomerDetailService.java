package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.CustomerDetailDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomerDetailService {
    CustomerDetailDto createCustomerDetail(CustomerDetailDto customerDetailDto);
}

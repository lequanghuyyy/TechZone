package com.springboot.shopbubu.controller;

import com.springboot.shopbubu.dto.CustomerDetailDto;
import com.springboot.shopbubu.dto.response.BaseResponse;
import com.springboot.shopbubu.dto.response.ResponseFactory;
import com.springboot.shopbubu.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customerDetail")
public class CustomerDetailController {
    private final CustomerDetailService customerDetailService;
    @Autowired
    public CustomerDetailController(CustomerDetailService customerDetailService) {
        this.customerDetailService = customerDetailService;
    }
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<CustomerDetailDto>> update(@Validated @RequestBody CustomerDetailDto customerDetailDto) {
        return ResponseFactory.ok(customerDetailService.update(customerDetailDto));
    }
}

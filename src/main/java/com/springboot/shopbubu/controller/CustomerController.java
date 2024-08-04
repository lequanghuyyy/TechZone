package com.springboot.shopbubu.controller;

import com.springboot.shopbubu.dto.CustomerDto;
import com.springboot.shopbubu.dto.CustomerSummaryDto;
import com.springboot.shopbubu.dto.response.BaseResponse;
import com.springboot.shopbubu.dto.response.CustomerResponse;
import com.springboot.shopbubu.dto.response.ResponseFactory;
import com.springboot.shopbubu.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/findAll")
    public ResponseEntity<BaseResponse<List<CustomerSummaryDto>>> getCustomer() {
            return ResponseFactory.ok(customerService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<BaseResponse<CustomerDto>> getCustomerById(@PathVariable Long id) {

            return ResponseFactory.ok(customerService.findById(id));

    }
    @GetMapping("/findByName/{name}")
    public ResponseEntity<BaseResponse<CustomerDto>> getCustomerByName(@PathVariable String name) {
            return ResponseFactory.ok(customerService.findByName(name));
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<CustomerDto>> createCustomer(@Validated @RequestBody CustomerDto customerDto) {
            return ResponseFactory.ok(customerService.create(customerDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseFactory.ok(null);

    }
}

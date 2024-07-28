package com.springboot.shopbubu.controller;

import com.springboot.shopbubu.dto.CustomerDto;
import com.springboot.shopbubu.dto.response.BaseResponse;
import com.springboot.shopbubu.dto.response.CustomerResponse;
import com.springboot.shopbubu.dto.response.ResponseFactory;
import com.springboot.shopbubu.service.CustomerService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse<List<CustomerDto>>> getCustomer() {
        try {
            return ResponseFactory.ok(customerService.findAll());
        }
       catch (RuntimeException e) {
            return ResponseFactory.error();
       }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<BaseResponse<CustomerDto>> getCustomerById(@PathVariable Long id) {
        try {
            return ResponseFactory.ok(customerService.findById(id));
        }
        catch (RuntimeException e) {
            return ResponseFactory.error();
        }
    }
    @GetMapping("/findByName/{name}")
    public ResponseEntity<BaseResponse<CustomerDto>> getCustomerByName(@PathVariable String name) {
        try {
            return ResponseFactory.ok(customerService.findByName(name));
        }
        catch (RuntimeException e) {
            return ResponseFactory.error();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<CustomerDto>> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            return ResponseFactory.ok(customerService.create(customerDto));
        }
        catch (RuntimeException e) {
            return ResponseFactory.error();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<CustomerDto>> updateCustomer(@RequestBody CustomerDto customerDto) {
        try {
            return ResponseFactory.ok(customerService.update(customerDto));
        }
        catch (RuntimeException e) {
            return ResponseFactory.error();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteById(id);
            return ResponseFactory.ok(null);
        }
        catch (RuntimeException e) {
            return ResponseFactory.error();
        }
    }
}

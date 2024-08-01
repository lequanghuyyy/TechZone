package com.springboot.shopbubu.controller;

import com.springboot.shopbubu.dto.OrderDto;
import com.springboot.shopbubu.dto.request.PaymentRequest;
import com.springboot.shopbubu.dto.response.BaseResponse;
import com.springboot.shopbubu.dto.response.ResponseFactory;
import com.springboot.shopbubu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/findAll")
    public ResponseEntity<BaseResponse<List<OrderDto>>> findAll() {

            return ResponseFactory.ok(orderService.findAll());


    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<BaseResponse<OrderDto>> findById(@PathVariable Long id) {

            return ResponseFactory.ok(orderService.findById(id));


    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<OrderDto>> create(@RequestBody PaymentRequest paymentRequest) {

            return ResponseFactory.ok(orderService.create(paymentRequest));

    }
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<OrderDto>> update(@RequestBody OrderDto orderDto) {

            return ResponseFactory.ok(orderService.update(orderDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
            orderService.delete(id);
            return ResponseFactory.ok(null);

    }
}

package com.springboot.shopbubu.controller;

import com.springboot.shopbubu.dto.CartDto;
import com.springboot.shopbubu.dto.response.BaseResponse;
import com.springboot.shopbubu.dto.response.ResponseFactory;
import com.springboot.shopbubu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/findAll")
    public ResponseEntity<BaseResponse<List<CartDto>>> findAll(){
        return ResponseFactory.ok(cartService.findALl());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<BaseResponse<CartDto>> findById(@PathVariable Long id){
        return ResponseFactory.ok(cartService.findById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<CartDto>> create(@RequestBody CartDto cartDto){
        return ResponseFactory.ok(cartService.create(cartDto));
    }
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<CartDto>> update(@RequestBody CartDto cartDto){
        return ResponseFactory.ok(cartService.update(cartDto));
    }
}

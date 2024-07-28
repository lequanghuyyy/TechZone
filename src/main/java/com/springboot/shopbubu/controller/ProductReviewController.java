package com.springboot.shopbubu.controller;

import com.springboot.shopbubu.dto.ProductReviewDto;
import com.springboot.shopbubu.dto.response.BaseResponse;
import com.springboot.shopbubu.dto.response.ResponseFactory;
import com.springboot.shopbubu.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productReview")
public class ProductReviewController {
    private final ProductReviewService productReviewService;
    @Autowired
    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<BaseResponse<ProductReviewDto>> findById(@PathVariable Long id) {
        try {
            return ResponseFactory.ok(productReviewService.findById(id));
        }
        catch (RuntimeException e){
            return ResponseFactory.error();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ProductReviewDto>> create(@RequestBody ProductReviewDto productReviewDto) {
        try {
            return ResponseFactory.ok(productReviewService.create(productReviewDto));
        }
        catch (RuntimeException e){
            return ResponseFactory.error();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<ProductReviewDto>> update(@RequestBody ProductReviewDto productReviewDto) {
        try {
            return ResponseFactory.ok(productReviewService.update(productReviewDto));
        }
        catch (RuntimeException e){
            return ResponseFactory.error();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
        try {
            productReviewService.delete(id);
            return ResponseFactory.ok(null);
        }
        catch (RuntimeException e){
            return ResponseFactory.error();
        }
    }
}

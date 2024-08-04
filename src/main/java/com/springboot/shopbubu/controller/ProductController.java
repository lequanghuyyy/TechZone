package com.springboot.shopbubu.controller;

import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.dto.paging.PageDto;
import com.springboot.shopbubu.dto.paging.ProductSearchRequest;
import com.springboot.shopbubu.dto.response.BaseResponse;
import com.springboot.shopbubu.dto.response.ResponseFactory;
import com.springboot.shopbubu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/findAll")

    public ResponseEntity<BaseResponse<PageDto<ProductDto>>> findAll(@RequestBody ProductSearchRequest productSearchRequest){
            return ResponseFactory.ok(productService.findAll(productSearchRequest));
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<BaseResponse<ProductDto>> findById(@PathVariable Long id){
            return ResponseFactory.ok(productService.findById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ProductDto>> create(@Validated @RequestBody ProductDto productDto){
            return ResponseFactory.ok(productService.create(productDto));
    }
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<ProductDto>> update(@Validated @RequestBody ProductDto productDto){
            return ResponseFactory.ok(productService.update(productDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id){
            productService.deleteById(id);
            return ResponseFactory.ok(null);
    }
    @PostMapping("uploadImage/{id}")
    public ResponseEntity<BaseResponse<Map>> uploadImage(@PathVariable Long id, MultipartFile file) throws IOException {
        return ResponseFactory.ok(productService.uploadImage(file, id));
    }

}

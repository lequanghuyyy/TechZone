package com.springboot.shopbubu.controller;

import com.springboot.shopbubu.dto.CategoryDto;
import com.springboot.shopbubu.dto.common.BaseResponse;
import com.springboot.shopbubu.dto.common.ResponseFactory;
import com.springboot.shopbubu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/findAll")
    public ResponseEntity<BaseResponse<List<CategoryDto>>> findAll() {
        return ResponseFactory.ok(categoryService.findAll());
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<CategoryDto>> create(@RequestBody CategoryDto categoryDto ) {
        return ResponseFactory.ok(categoryService.create(categoryDto));
    }
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<CategoryDto>> update(@RequestBody CategoryDto categoryDto ) {
        return ResponseFactory.ok(categoryService.update(categoryDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseFactory.ok(null);
    }
}

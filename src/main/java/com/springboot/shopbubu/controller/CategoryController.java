package com.springboot.shopbubu.controller;

import com.springboot.shopbubu.dto.CategoryDto;
import com.springboot.shopbubu.dto.response.BaseResponse;
import com.springboot.shopbubu.dto.response.ResponseFactory;
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
        try {
           return ResponseFactory.ok(categoryService.findAll());
        }
        catch (RuntimeException e) {
          return ResponseFactory.error();
        }

    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<CategoryDto>> create(@RequestBody CategoryDto categoryDto ) {
        try { return ResponseFactory.ok(categoryService.create(categoryDto));}
        catch (RuntimeException e) {
            return ResponseFactory.error();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<CategoryDto>> update(@RequestBody CategoryDto categoryDto ) {
         try{ return ResponseFactory.ok(categoryService.update(categoryDto));}
        catch (RuntimeException e) {
            return ResponseFactory.error();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseFactory.ok(null);
    }
}

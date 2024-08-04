package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.dto.paging.PageDto;
import com.springboot.shopbubu.dto.paging.ProductSearchRequest;
import com.springboot.shopbubu.dto.request.ProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface ProductService {
    PageDto<ProductDto> findAll(ProductSearchRequest productRequest);
    ProductDto create(ProductDto productDto);
    ProductDto findById(Long id);
    ProductDto update(ProductDto productDto);
    void deleteById(Long id);
    Map uploadImage(MultipartFile file,Long id) throws IOException;
}

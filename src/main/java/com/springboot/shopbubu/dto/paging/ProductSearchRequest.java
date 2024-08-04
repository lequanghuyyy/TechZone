package com.springboot.shopbubu.dto.paging;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSearchRequest {
    private String keyword;

    @Min(value = 1, message = "Page must be at least 1")
    @Max(value = 1000, message = "Page must be at most 100")
    private int page = 1;

    @Min(value = 1, message = "Size must be at least 1")
    @Max(value = 100, message = "Size must be at most 100")
    private int size = 10;

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}

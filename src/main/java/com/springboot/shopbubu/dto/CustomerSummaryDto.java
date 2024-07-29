package com.springboot.shopbubu.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class CustomerSummaryDto {
    private Long id;
    private String name;
    private String gender;
    private Long userId;
}

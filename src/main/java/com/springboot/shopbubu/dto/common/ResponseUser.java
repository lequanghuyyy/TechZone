package com.springboot.shopbubu.dto.common;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseUser {
    private Long id;

    private String username;

    @NotEmpty
    private List<String> roles;
}

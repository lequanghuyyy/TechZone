package com.springboot.shopbubu.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}

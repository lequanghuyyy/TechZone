package com.springboot.shopbubu.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter@Builder
public class TokenResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
    private String userId;
}

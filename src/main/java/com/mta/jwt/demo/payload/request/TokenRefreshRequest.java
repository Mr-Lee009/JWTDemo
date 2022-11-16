package com.mta.jwt.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenRefreshRequest {
    private String refreshToken;
}

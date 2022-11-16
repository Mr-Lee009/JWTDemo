package com.mta.jwt.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {
    private Long id;
    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";
    private String username;
    private String email;

    private List<String> roles;

    public JwtResponse(Long id, String accessToken,String refreshToken, String username,
                       String email, List<String> roles) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}

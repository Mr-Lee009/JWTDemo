package com.mta.jwt.demo.jwt;

import java.util.List;

public class JwtResponse {
    private Long id;
    private String accessToken;
    private String type = "Bearer";
    private String username;
    private String email;
    private String refreshToken;
    private List<String> roles;

    public JwtResponse(Long id, String accessToken, String username, String email, String refreshToken, List<String> roles) {
        this.id = id;
        this.accessToken = accessToken;
        this.username = username;
        this.email = email;
        this.refreshToken = refreshToken;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

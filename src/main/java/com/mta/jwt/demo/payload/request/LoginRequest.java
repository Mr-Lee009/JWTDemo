package com.mta.jwt.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    private String username;
    private String password;
}

//        – Requests:
//
//        LoginRequest: { username, password }
//        SignupRequest: { username, email, password }

//        – Responses:
//
//        JwtResponse: { token, type, id, username, email, roles }
//        MessageResponse: { message }

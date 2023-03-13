package com.mta.jwt.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public static ErrorMessage lockMessage() {
        return ErrorMessage.builder()
                .statusCode(401)
                .description("Account Locked")
                .message("Your account has been locked due to 3 failed attempts.It will be unlocked after 24 hours.")
                .build();

    }

    public static Object unLockMessage() {
        return ErrorMessage.builder()
                .statusCode(401)
                .description("Account Locked")
                .message("Your account has been unlocked. Please try to login again.")
                .build();
    }
}

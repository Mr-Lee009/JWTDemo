package com.mta.jwt.demo.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {

    //Annotation @ApiModelProperty dùng để giải thích các field của model trên swagger
    @ApiModelProperty(notes = "username of account.")
    private String username;
    @ApiModelProperty(notes = "email of account.")
    private String email;
    @ApiModelProperty(notes = "password of account.")
    private String password;
    @ApiModelProperty(notes = "roles of account.")
    private Set<String> role;
}

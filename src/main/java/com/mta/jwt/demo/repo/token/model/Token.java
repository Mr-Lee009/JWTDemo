package com.mta.jwt.demo.repo.token.model;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String accessToken;
    private String refeshToken;
}

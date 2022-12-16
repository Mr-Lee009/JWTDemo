package com.mta.jwt.demo.user.model;


import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class User {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String username;
    private String pass;
}

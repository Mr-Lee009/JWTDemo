package com.mta.jwt.demo.repo.user.model;


import lombok.Data;

import javax.persistence.*;

@Table(name = "User")
@Entity
@Data
public class User {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String username;
    private String pass;
}

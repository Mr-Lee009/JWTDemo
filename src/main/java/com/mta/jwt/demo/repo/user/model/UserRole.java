package com.mta.jwt.demo.repo.user.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user_role")
@Entity
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private int userId;
    @Column(name = "role_id")
    private int roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.mta.jwt.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String username;
    private String email;
    private String password;
    private String currentAddress;
    private boolean isEnabled;
    @Column(name = "failed_attempt", columnDefinition = "int(10) default 0 ")
    private Integer failedAttempt;
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;
    @Column(name = "lock_time")
    //@Nullable
    private Date lockTime;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

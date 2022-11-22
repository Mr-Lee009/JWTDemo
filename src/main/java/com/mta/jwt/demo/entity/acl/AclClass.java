package com.mta.jwt.demo.entity.acl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ACL_ClASS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AclClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CLASS")
    private Integer Class;

}







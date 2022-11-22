package com.mta.jwt.demo.entity.acl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ACL_SID")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AclSID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //SID: which is the username or role name. SID stands for Security Identity
    @Column(name = "SID")
    private String sid;

    //PRINCIPAL: 0 or 1, to indicate that the corresponding SID is a principal (user, such as mary, mike, jack…) or an authority (role, such as ROLE_ADMIN, ROLE_USER, ROLE_EDITOR…)
    @Column(name = "PRINCIPAL")
    private Integer principal;

}

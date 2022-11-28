package com.mta.jwt.demo.entity.acl;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ACL_SID")
@Data
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

    @OneToMany(mappedBy = "aclSID", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<AclObjectIdentity> aclObjectIdentities;
}

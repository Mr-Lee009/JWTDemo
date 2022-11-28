package com.mta.jwt.demo.entity.acl;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ACL_ClASS")
@Data
public class AclClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CLAZZ")
    private Integer Clazz;

    @OneToMany(mappedBy = "aclClass", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<AclObjectIdentity> aclObjectIdentities;

}







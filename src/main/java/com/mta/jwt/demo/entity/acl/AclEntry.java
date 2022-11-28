package com.mta.jwt.demo.entity.acl;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ACL_ENTRY")
@Data
public class AclEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //ACL_OBJECT_IDENTITY: specify the object identity, links to ACL_OBJECT_IDENTITY table
//    @Column(name = "ACL_OBJECT_IDENTITY")
//    private Integer aclObjectIdentity;

    //ACE_ORDER: the order of current entry in the ACL entries list of corresponding Object Identity
    @Column(name = "ACE_ORDER")
    private Integer aclOrder;

    //SID: the target SID which the permission is granted to or denied from, links to ACL_SID table
//    @Column(name = "SID")
//    private Integer sid;
    
    //MASK: the integer bit mask that represents the actual permission being granted or denied
    @Column(name = "MASK")
    private Integer mask;
    
    //GRANTING: value 1 means granting, value 0 means denying
    @Column(name = "GRANTING")
    private Integer granting;
    
    //AUDIT_SUCCESS and AUDIT_FAILURE: for auditing purpose
    @Column(name = "AUDIT_SUCCESS")
    private Integer auditSuccess;

    @ManyToOne
    @JoinColumn(name = "ACL_OBJECT_IDENTITY") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AclObjectIdentity aclObjectIdentity;


    @ManyToOne
    @JoinColumn(name = "SID") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AclSID aclSID;
}

package com.mta.jwt.demo.entity.acl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ACL_OBJECT_IDENTITY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AclObjectIdentity {

    //OBJECT_ID_CLASS: define the domain object class, links to ACL_CLASS table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //OBJECT_ID_CLASS: define the domain object class, links to ACL_CLASS table
    @Column(name = "OBJECT_ID_CLASS")
    private Integer objectIdClass;

    //OBJECT_ID_IDENTITY: domain objects can be stored in many tables depending on the class. Hence, this field store the target object primary key
    @Column(name = "OBJECT_ID_IDENTITY")
    private Integer objectIdIdentity;

    //PARENT_OBJECT: specify parent of this Object Identity within this table
    @Column(name = "PARENT_OBJECT")
    private Integer parentObject;

    //OWNER_SID: ID of the object owner, links to ACL_SID table
    @Column(name = "OWNER_SID")
    private Integer ownerSid;

    //ENTRIES_INHERITING: whether ACL Entries of this object inherits from the parent object (ACL Entries are defined in ACL_ENTRY table)
    @Column(name = "ENTRIES_INHERITING")
    private Integer entriesIngeriting;

}

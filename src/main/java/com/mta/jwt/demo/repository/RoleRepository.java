package com.mta.jwt.demo.repository;

import com.mta.jwt.demo.entity.ERole;
import com.mta.jwt.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}

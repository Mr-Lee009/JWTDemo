package com.mta.jwt.demo.repository;

import com.mta.jwt.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    @Override
    Page<User> findAll(Pageable pageable);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE (u.email = ?2) ")
    @Modifying
    public void updateFailedAttempts(int failAttempts, String email);
}

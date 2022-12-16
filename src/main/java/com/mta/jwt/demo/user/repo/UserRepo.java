package com.mta.jwt.demo.user.repo;

import com.mta.jwt.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}

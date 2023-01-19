package com.mta.jwt.demo.repo.user.repo;

import com.mta.jwt.demo.repo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}

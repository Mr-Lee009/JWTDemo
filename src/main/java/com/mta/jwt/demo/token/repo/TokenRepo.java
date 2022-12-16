package com.mta.jwt.demo.token.repo;

import com.mta.jwt.demo.token.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token,Long> {
}

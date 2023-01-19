package com.mta.jwt.demo.repo.payment.repo;

import com.mta.jwt.demo.repo.payment.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card,Long> {
}

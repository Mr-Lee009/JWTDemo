package com.mta.jwt.demo.payment.repo;

import com.mta.jwt.demo.payment.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card,Long> {
}

package com.mta.jwt.demo.repo.payment.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import javax.persistence.Id;

@Entity
@Data
@Table(name = "PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "string_amount")
    private String stringAmount;
    @Column(name = "order_info")
    private String orderInfo;
    @Column(name = "description")
    private String description;
    @Column(name = "signature")
    private String signature;

}

package com.mta.jwt.demo.payment.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String Name;
    private String ExprirationMoth;
}

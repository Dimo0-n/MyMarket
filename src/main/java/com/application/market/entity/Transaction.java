package com.application.market.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "surname", nullable = false)
    String surname;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "card_number", nullable = false)
    String cardNumber;

    @Column(name = "data_of_transaction", nullable = false)
    LocalDateTime dataOfTransaction;

    @Column(name = "total_price", nullable = false)
    Double totalPrice;

}

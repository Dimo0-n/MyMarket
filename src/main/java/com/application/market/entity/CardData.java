package com.application.market.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardData {

    private String cardNumber;
    private String cardName;
    private String expirationMonth;
    private String expirationYear;
    private String ccv;

}

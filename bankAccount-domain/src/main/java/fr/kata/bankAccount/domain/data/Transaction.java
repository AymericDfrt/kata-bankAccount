package fr.kata.bankaccount.domain.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    private BigDecimal amount;
    private String currency;
    /*todo : To be deleted when authentication is implemented ! */
    private Long ownerId;
    private Bank bank;
}

package fr.kata.bankaccount.domain.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {

    private Long accountId;
    private Long ownerId;
    private Balance balance;
    private LocalDateTime createdAt;

}

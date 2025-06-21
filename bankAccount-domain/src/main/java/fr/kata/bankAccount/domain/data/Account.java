package fr.kata.bankAccount.domain.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class Account {

    private Long accountId;
    private Long ownerId;
    private Balance balance;
    private LocalDateTime createdAt;

}

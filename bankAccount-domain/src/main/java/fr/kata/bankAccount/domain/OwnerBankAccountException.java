package fr.kata.bankAccount.domain;

import lombok.Getter;

@Getter
public class OwnerBankAccountException extends RuntimeException {
    private final String message;
    private final int status;

    public OwnerBankAccountException(String message, int status) {
        this.message = message;
        this.status = status;
    }
}

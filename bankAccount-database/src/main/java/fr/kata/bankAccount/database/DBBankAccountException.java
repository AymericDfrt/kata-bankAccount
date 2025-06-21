package fr.kata.bankAccount.database;

import lombok.Getter;

@Getter
public class DBBankAccountException extends RuntimeException {
    private final String message;
    private final int status;

    public DBBankAccountException(String message, int status) {
        this.message = message;
        this.status = status;
    }
}

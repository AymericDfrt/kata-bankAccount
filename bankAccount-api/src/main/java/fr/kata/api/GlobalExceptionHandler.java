package fr.kata.api;

import fr.kata.bankAccount.database.DBBankAccountException;
import fr.kata.bankAccount.domain.OwnerBankAccountException;
import fr.kata.bankAccount.swagger.bo.GenericError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OwnerBankAccountException.class)
    public ResponseEntity<GenericError> handleBankAccountException(OwnerBankAccountException ex) {
        return new ResponseEntity<>(new GenericError().message(ex.getMessage()), HttpStatus.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(DBBankAccountException.class)
    public ResponseEntity<GenericError> handleDatabaseBankAccountException(DBBankAccountException ex) {
        return new ResponseEntity<>(new GenericError().message(ex.getMessage()), HttpStatus.valueOf(ex.getStatus()));
    }
}

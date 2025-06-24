package fr.kata.bankaccount.api;

import fr.kata.bankaccount.api.mapper.BalanceMapper;
import fr.kata.bankaccount.api.mapper.TransactionMapper;
import fr.kata.bankaccount.domain.OwnerBankAccountService;
import fr.kata.bankaccount.swagger.bo.Balance;
import fr.kata.bankaccount.swagger.bo.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/account/")
public class AccountController {

    private final OwnerBankAccountService ownerBankAccountService;
    private final BalanceMapper balanceMapper;
    private final TransactionMapper transactionMapper;

    @PostMapping(value = "{accountId}/deposit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Balance> depositToAccount(@PathVariable("accountId") Long accountId, @RequestBody Transaction transaction) {

        var balance = ownerBankAccountService.deposit(accountId, transactionMapper.map(transaction));
        return new ResponseEntity<>(balanceMapper.map(balance), HttpStatus.OK);
    }

    @PostMapping(value = "{accountId}/withdraw", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Balance> withdrawFromAccount(@PathVariable("accountId") Long accountId, @RequestBody Transaction transaction) {
        var balance = ownerBankAccountService.withdraw(accountId, transactionMapper.map(transaction));
        return new ResponseEntity<>(balanceMapper.map(balance), HttpStatus.OK);
    }
}

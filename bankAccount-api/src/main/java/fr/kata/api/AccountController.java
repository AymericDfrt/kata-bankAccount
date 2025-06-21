package fr.kata.api;

import fr.kata.bankAccount.domain.OwnerBankAccountService;
import fr.kata.bankAccount.domain.data.Bank;
import fr.kata.bankAccount.swagger.bo.Balance;
import fr.kata.bankAccount.swagger.bo.Transaction;
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

    @PostMapping(value = "{accountId}/deposit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Balance> depositToAccount(@PathVariable("accountId") String accountId, @RequestBody Transaction transaction) {
        var balance = ownerBankAccountService.deposit(Long.valueOf(accountId), transaction.getOwnerId(), transaction.getAmount(), Enum.valueOf(Bank.class, transaction.getBank().name()));
        return new ResponseEntity<>(balanceMapper.map(balance), HttpStatus.OK);
    }

    @PostMapping(value = "{accountId}/withdraw", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Balance> withdrawFromAccount(@PathVariable("accountId") String accountId, @RequestBody Transaction transaction) {
        var balance = ownerBankAccountService.withdraw(Long.valueOf(accountId), transaction.getOwnerId(), transaction.getAmount(), Enum.valueOf(Bank.class, transaction.getBank().name()));
        return new ResponseEntity<>(balanceMapper.map(balance), HttpStatus.OK);
    }
}

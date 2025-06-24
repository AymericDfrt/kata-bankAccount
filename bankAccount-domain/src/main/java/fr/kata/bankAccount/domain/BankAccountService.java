package fr.kata.bankAccount.domain;

import fr.kata.bankAccount.domain.data.Account;
import fr.kata.bankAccount.domain.data.Bank;

import java.math.BigDecimal;

public interface BankAccountService {

    Account getAccount(Long ownerId, Long accountId);

    Account deposit(Long accountId, Long ownerId, BigDecimal amount);

    Account withdraw(Long accountId, Long ownerId, BigDecimal amount);

    boolean isOwnerBank(Bank ownerBank);
}

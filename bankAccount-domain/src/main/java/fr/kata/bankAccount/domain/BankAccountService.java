package fr.kata.bankAccount.domain;

import fr.kata.bankAccount.domain.data.Account;
import fr.kata.bankAccount.domain.data.Bank;

public interface BankAccountService {

    Account getAccount(Long ownerId, Long accountId);

    Account deposit(Long accountId, Long ownerId, Float amount);

    Account withdraw(Long accountId, Long ownerId, Float amount);

    boolean isOwnerBank(Bank ownerBank);
}

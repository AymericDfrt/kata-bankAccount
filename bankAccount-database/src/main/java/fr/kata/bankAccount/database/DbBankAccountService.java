package fr.kata.bankaccount.database;


import fr.kata.bankaccount.domain.BankAccountService;
import fr.kata.bankaccount.domain.OwnerBankAccountException;
import fr.kata.bankaccount.domain.data.Bank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class DbBankAccountService implements BankAccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public boolean isOwnerBank(Bank ownerBank) {
        return Bank.SG == ownerBank;
    }

    public fr.kata.bankaccount.domain.data.Account getAccount(Long accountId, Long ownerId) {
        return accountMapper.mapAccount(getAccountEntity(accountId, ownerId));
    }

    public fr.kata.bankaccount.domain.data.Account deposit(Long accountId, Long ownerId, BigDecimal amount) {
        try {
            var account = getAccountEntity(accountId, ownerId);
            var totalBalance = account.getBalance().add(amount);
            account.setBalance(totalBalance);
            accountRepository.save(account);
            return getAccount(accountId, ownerId);
        } catch (Exception e) {
            throw new OwnerBankAccountException(e.getMessage(), 500);
        }
    }

    public fr.kata.bankaccount.domain.data.Account withdraw(Long accountId, Long ownerId, BigDecimal amount) {
        try {
            var account = getAccountEntity(accountId, ownerId);
            var totalBalance = account.getBalance().subtract(amount);
            account.setBalance(totalBalance);
            accountRepository.save(account);
            return getAccount(accountId, ownerId);
        } catch (Exception ex) {
            throw new DBBankAccountException("test", 500);
        }
    }

    private Account getAccountEntity(Long accountId, Long ownerId) {
        return accountRepository.findByIdAndOwnerId(accountId, ownerId).orElseThrow(() -> new OwnerBankAccountException("Account not found", 404));
    }
}

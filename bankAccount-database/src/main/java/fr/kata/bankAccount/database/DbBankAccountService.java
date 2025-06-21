package fr.kata.bankAccount.database;


import fr.kata.bankAccount.domain.BankAccountService;
import fr.kata.bankAccount.domain.OwnerBankAccountException;
import fr.kata.bankAccount.domain.data.Bank;
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

    public fr.kata.bankAccount.domain.data.Account getAccount(Long accountId, Long ownerId) {
        return accountMapper.mapAccount(getAccountEntity(ownerId, accountId));
    }

    public fr.kata.bankAccount.domain.data.Account deposit(Long accountId, Long ownerId, Float amount) {
        try {
            var account = getAccountEntity(ownerId, accountId);
            var totalBalance = account.getBalance().add(new BigDecimal(Float.toString(amount)));
            account.setBalance(totalBalance);
            accountRepository.save(account);
            return getAccount(accountId, ownerId);
        } catch (Exception e) {
            throw new OwnerBankAccountException(e.getMessage(), 500);
        }
    }

    public fr.kata.bankAccount.domain.data.Account withdraw(Long accountId, Long ownerId, Float amount) {
        try {
            var account = getAccountEntity(ownerId, accountId);
            var totalBalance = account.getBalance().subtract(new BigDecimal(Float.toString(amount)));
            account.setBalance(totalBalance);
            accountRepository.save(account);
            return getAccount(ownerId, accountId);
        } catch (Exception ex) {
            throw new DBBankAccountException("test", 500);
        }
    }

    private Account getAccountEntity(Long ownerId, Long accountId) {
        return accountRepository.findByIdAndOwnerId(accountId, ownerId).orElseThrow(() -> new OwnerBankAccountException("Account not found", 404));
    }
}

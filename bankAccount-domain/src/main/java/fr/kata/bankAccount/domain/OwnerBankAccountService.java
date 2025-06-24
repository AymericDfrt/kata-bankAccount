package fr.kata.bankaccount.domain;

import fr.kata.bankaccount.domain.data.Balance;
import fr.kata.bankaccount.domain.data.Bank;
import fr.kata.bankaccount.domain.data.Transaction;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
public class OwnerBankAccountService {

    /**
     * List of services attached to different banks
     */
    private List<BankAccountService> listBankAccountService;

    public Balance deposit(Long accountId, Transaction transaction) {
        checkValidAmount(transaction.getAmount());
        var ownerBankService = getOwnerBankService(transaction.getBank());
        var accountFinal = ownerBankService.deposit(accountId, transaction.getOwnerId(), transaction.getAmount());
        return accountFinal.getBalance();
    }

    public Balance withdraw(Long accountId, Transaction transaction) {
        checkValidAmount(transaction.getAmount());
        var ownerBankService = getOwnerBankService(transaction.getBank());
        checkAmountAuthorized(ownerBankService, accountId, transaction.getOwnerId(), transaction.getAmount());
        var accountFinal = ownerBankService.withdraw(accountId, transaction.getOwnerId(), transaction.getAmount());
        return accountFinal.getBalance();
    }

    private void checkValidAmount(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal(BigInteger.ZERO)) < 0) {
            throw new OwnerBankAccountException("The amount cannot be negative", 400);
        }
    }

    private void checkAmountAuthorized(BankAccountService ownerBankAccount, Long accountId, Long ownerId, BigDecimal amount) {
        var account = ownerBankAccount.getAccount(ownerId, accountId);

        if (account.getBalance().getTotalBalance().subtract(amount).compareTo(new BigDecimal(BigInteger.ZERO)) < 0) {
            throw new OwnerBankAccountException("The amount must not exceed the account balance", 400);
        }
    }

    private BankAccountService getOwnerBankService(Bank ownerBank) {
        return listBankAccountService.stream()
                .filter(bankAccountService -> bankAccountService.isOwnerBank(ownerBank))
                .findFirst()
                .orElseThrow(() -> new OwnerBankAccountException("The customer's bank was not found.", 404));
    }
}

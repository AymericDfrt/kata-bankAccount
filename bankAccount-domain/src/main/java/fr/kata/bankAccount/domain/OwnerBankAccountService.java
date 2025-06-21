package fr.kata.bankAccount.domain;

import fr.kata.bankAccount.domain.data.Balance;
import fr.kata.bankAccount.domain.data.Bank;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class OwnerBankAccountService {

    /**
     * List of services attached to different banks
     */
    private List<BankAccountService> listBankAccountService;

    public Balance deposit(Long accountId, Long ownerId, Float amount, Bank ownerBank) {
        checkValidAmount(amount);
        var ownerBankService = getOwnerBankService(ownerBank);
        var accountFinal = ownerBankService.deposit(accountId, ownerId, amount);
        return accountFinal.getBalance();
    }

    public Balance withdraw(Long accountId, Long ownerId, Float amount, Bank ownerBank) {
        checkValidAmount(amount);
        var ownerBankService = getOwnerBankService(ownerBank);
        checkAmountAuthorized(ownerBankService, accountId, ownerId, amount);
        var accountFinal = ownerBankService.withdraw(accountId, ownerId, amount);
        return accountFinal.getBalance();
    }

    private void checkValidAmount(Float amount) {
        if (amount < 0) {
            throw new OwnerBankAccountException("The amount cannot be negative", 400);
        }
    }

    private void checkAmountAuthorized(BankAccountService ownerBankAccount, Long accountId, Long ownerId, Float amount) {
        var account = ownerBankAccount.getAccount(ownerId, accountId);

        if (account.getBalance().getTotalBalance().subtract(new BigDecimal(Float.toString(amount))).compareTo(new BigDecimal("0")) < 0) {
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

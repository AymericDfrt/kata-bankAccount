package fr.kata.api.mapper;

import fr.kata.bankAccount.domain.data.Bank;
import fr.kata.bankAccount.domain.data.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {

    Transaction map(fr.kata.bankAccount.swagger.bo.Transaction transaction);

    default Bank getBank(fr.kata.bankAccount.swagger.bo.Transaction.BankEnum bankEnum) {
        return Enum.valueOf(Bank.class, bankEnum.name());
    }
}

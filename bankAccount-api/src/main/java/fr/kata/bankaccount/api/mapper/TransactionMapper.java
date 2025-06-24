package fr.kata.bankaccount.api.mapper;

import fr.kata.bankaccount.domain.data.Bank;
import fr.kata.bankaccount.domain.data.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {

    Transaction map(fr.kata.bankaccount.swagger.bo.Transaction transaction);

    default Bank getBank(fr.kata.bankaccount.swagger.bo.Transaction.BankEnum bankEnum) {
        return Enum.valueOf(Bank.class, bankEnum.name());
    }
}

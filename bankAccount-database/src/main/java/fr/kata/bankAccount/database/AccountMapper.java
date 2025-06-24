package fr.kata.bankaccount.database;

import fr.kata.bankaccount.domain.data.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AccountMapper {

    @Mapping(target = "accountId", source = "id")
    @Mapping(target = "balance.totalBalance", source = "balance")
    @Mapping(target = "balance.currency", source = "currency")
    Account mapAccount(fr.kata.bankaccount.database.Account account);

}

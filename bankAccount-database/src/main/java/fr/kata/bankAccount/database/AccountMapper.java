package fr.kata.bankAccount.database;

import fr.kata.bankAccount.domain.data.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AccountMapper {

    @Mapping(target = "accountId", source = "id")
    @Mapping(target = "balance.totalBalance", source = "balance")
    @Mapping(target = "balance.currency", source = "currency")
    Account mapAccount(fr.kata.bankAccount.database.Account account);

}

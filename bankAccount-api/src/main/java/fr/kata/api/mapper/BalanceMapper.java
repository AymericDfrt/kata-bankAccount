package fr.kata.api.mapper;

import fr.kata.bankAccount.swagger.bo.Balance;
import org.mapstruct.Mapper;

@Mapper
public interface BalanceMapper {
    Balance map(fr.kata.bankAccount.domain.data.Balance balance);
}

package fr.kata.bankaccount.api.mapper;

import fr.kata.bankaccount.swagger.bo.Balance;
import org.mapstruct.Mapper;

@Mapper
public interface BalanceMapper {
    Balance map(fr.kata.bankaccount.domain.data.Balance balance);
}

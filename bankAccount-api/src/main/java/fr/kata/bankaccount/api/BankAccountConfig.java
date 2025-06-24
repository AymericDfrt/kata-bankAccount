package fr.kata.bankaccount.api;

import fr.kata.bankaccount.api.mapper.BalanceMapper;
import fr.kata.bankaccount.api.mapper.BalanceMapperImpl;
import fr.kata.bankaccount.api.mapper.TransactionMapper;
import fr.kata.bankaccount.api.mapper.TransactionMapperImpl;
import fr.kata.bankaccount.database.DbBankAccountService;
import fr.kata.bankaccount.domain.OwnerBankAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BankAccountConfig {

    @Bean
    public BalanceMapper balanceMapper() {
        return new BalanceMapperImpl();
    }

    @Bean
    public TransactionMapper transactionMapper() {
        return new TransactionMapperImpl();
    }

    @Bean
    public OwnerBankAccountService ownerBankAccountService(DbBankAccountService dbBankAccountService) {
        return new OwnerBankAccountService(List.of(dbBankAccountService));
    }

}

package fr.kata.api;

import fr.kata.api.mapper.BalanceMapper;
import fr.kata.api.mapper.BalanceMapperImpl;
import fr.kata.api.mapper.TransactionMapper;
import fr.kata.api.mapper.TransactionMapperImpl;
import fr.kata.bankAccount.database.DbBankAccountService;
import fr.kata.bankAccount.domain.OwnerBankAccountService;
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

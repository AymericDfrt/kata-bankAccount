package fr.kata.api;

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
    public OwnerBankAccountService ownerBankAccountService(DbBankAccountService dbBankAccountService) {
        return new OwnerBankAccountService(List.of(dbBankAccountService));
    }

}

package fr.kata.bankAccount.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBBankAccountConfig {

    @Bean
    public AccountMapper accountMapper() {
        return new AccountMapperImpl();
    }

    @Bean
    public DbBankAccountService dbBankAccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        return new DbBankAccountService(accountRepository, accountMapper);
    }
}

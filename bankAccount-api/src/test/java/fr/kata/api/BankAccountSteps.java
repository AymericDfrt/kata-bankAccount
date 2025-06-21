package fr.kata.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kata.bankAccount.database.Account;
import fr.kata.bankAccount.database.AccountRepository;
import fr.kata.bankAccount.swagger.bo.Balance;
import fr.kata.bankAccount.swagger.bo.GenericError;
import fr.kata.bankAccount.swagger.bo.Transaction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;


public class BankAccountSteps {

    @Autowired
    private AccountRepository accountRepository;  // <-- mock

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> responseEntity;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Long ownerId;
    private Transaction.BankEnum bank;
    private Long accountId;
    private Float amount;

    @Given("I am owner number {}")
    public void i_am_owner_number(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Given("I am in {} bank")
    public void i_am_owner_number(Transaction.BankEnum bank) {
        this.bank = bank;
    }

    @Given("I have account number {}")
    public void i_have_account_number(Long accountId) {
        this.accountId = accountId;
    }

    @Given("I want to withdraw {} euros")
    public void i_want_to_withdraw__euros(Float amount) {
        this.amount = amount;
    }

    @When("The account belongs to the owner")
    public void the_account_belongs_to_the_owner() {
        Mockito.when(accountRepository.existsByIdAndOwnerId(anyLong(), anyLong())).thenReturn(true);
    }

    @When("The account does not belong to him")
    public void the_account_does_not_belong_to_him() {
        Mockito.when(accountRepository.existsByIdAndOwnerId(anyLong(), anyLong())).thenReturn(false);
    }

    @When("The account has {} euros")
    public void the_account_has__euros(Float balance) {
        var account = Account.builder()
                .id(accountId)
                .balance(new BigDecimal(Float.toString(balance)))
                .currency("EUR")
                .ownerId(ownerId)
                .createdAt(LocalDateTime.now())
                .build();

        Mockito.when(accountRepository.findByIdAndOwnerId(anyLong(), anyLong())).thenReturn(Optional.of(account));
    }

    @When("The account is not found")
    public void the_account_is_not_found() {
        Mockito.when(accountRepository.findByIdAndOwnerId(anyLong(), anyLong())).thenReturn(Optional.empty());
    }

    @Then("I make the deposit")
    public void i_make_the_deposit() {
        Map<String, Object> request = Map.of("amount", this.amount, "currency", "EUR", "ownerId", ownerId, "bank", bank.name());
        responseEntity = restTemplate.postForEntity(
                "/api/account/" + accountId + "/deposit", request, String.class);

    }

    @Then("I make the withdraw")
    public void i_make_the_withdraw() {
        Map<String, Object> request = Map.of("amount", this.amount, "currency", "EUR", "ownerId", ownerId, "bank", bank.name());
        responseEntity = restTemplate.postForEntity(
                "/api/account/" + accountId + "/withdraw", request, String.class);

    }


    @Then("My total balance is {} euros")
    public void my_total_balancce_is(Float totalBalance) throws JsonProcessingException {
        var balance = objectMapper.readValue(responseEntity.getBody(), Balance.class);
        assertEquals(balance.getTotalBalance(), totalBalance);
    }

    @Then("I encounter the error {}")
    public void i_encounter_the_error(String message) throws JsonProcessingException {
        var error = objectMapper.readValue(responseEntity.getBody(), GenericError.class);
        assertEquals(error.getMessage(), message);
    }

}

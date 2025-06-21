Feature: bank account deposit

  Scenario: A customer wants to deposit money into an account that does not belong to him
    Given I am owner number 1
    And I am in SG bank
    And I have account number 3
    And I want to withdraw 500 euros
    When The account is not found
    Then I make the deposit
    And I encounter the error Account not found

  Scenario: A customer wants to make a deposit with an account from a bank that is not yet managed
    Given I am owner number 1
    And I am in CDN bank
    And I have account number 3
    And I want to withdraw 500 euros
    Then I make the deposit
    And I encounter the error The customer's bank was not found.

  Scenario: A customer makes a deposit with an incorrect amount
    Given I am owner number 1
    And I am in SG bank
    And I have account number 1
    And I want to withdraw -100 euros
    Then I make the deposit
    And I encounter the error The amount cannot be negative

  Scenario Outline: A customer successfully makes a deposit
    Given I am owner number 1
    And I am in SG bank
    And I have account number 1
    And I want to withdraw <amount> euros
    When The account has <balance> euros
    Then I make the deposit
    And My total balance is <totalBalance> euros

    Examples:
      | amount  | balance | totalBalance |
      | 500     | 600     | 1100         |
      | 100     | 500     | 600          |
      | 1500.85 | 1730.50 | 3231.35      |
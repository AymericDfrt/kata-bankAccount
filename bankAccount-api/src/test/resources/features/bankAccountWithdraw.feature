Feature: bank account withdraw

  Scenario: A customer wants to make a withdrawal from an account that does not belong to him
    Given I am owner number 1
    And I am in SG bank
    And I have account number 3
    And I want to withdraw 500 euros
    When The account is not found
    Then I make the withdraw
    And I encounter the error Account not found

  Scenario: A customer wants to make a withdrawal with an account from a bank that is not yet managed
    Given I am owner number 1
    And I am in CDN bank
    And I have account number 3
    And I want to withdraw 500 euros
    Then I make the withdraw
    And I encounter the error The customer's bank was not found.

  Scenario Outline: A customer makes a withdrawal with an incorrect amount
    Given I am owner number 1
    And I am in SG bank
    And I have account number 1
    And I want to withdraw <amount> euros
    When The account has <balance> euros
    Then I make the withdraw
    And I encounter the error <message>

    Examples:
      | amount | balance | message                                        |
      | 1000   | 600     | The amount must not exceed the account balance |
      | -50    | 500     | The amount cannot be negative                  |

  Scenario Outline: A customer successfully makes a withdrawal
    Given I am owner number 1
    And I am in SG bank
    And I have account number 1
    And I want to withdraw <amount> euros
    When The account has <balance> euros
    Then I make the withdraw
    And My total balance is <totalBalance> euros

    Examples:
      | amount  | balance | totalBalance |
      | 500     | 600     | 100          |
      | 100     | 500     | 400          |
      | 1500.85 | 1730.50 | 229.65       |
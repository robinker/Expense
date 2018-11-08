Feature: Add a transaction
    As a user
    I want to add income and expense

Background:
    Given a user with balance 500

Scenario: Deposit
    When I deposit 100
    Then my account have balance 600

Scenario: Expense
    When I expense 100
    Then my account have balance 400

Scenario: Expense more than Balance
    When I expense 550
    Then my account is in debt for 50
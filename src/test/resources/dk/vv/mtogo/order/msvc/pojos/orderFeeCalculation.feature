Feature: Calculate the fee for mtogo
  Scenario: order total is less than 101 -- 6% fee
    Given I have an order with a total of 100.00 and a subtotal of 80.00
    When I calculate the fee
    Then The fee should be 4.80

    Given I have an order with a total of 100.99 and a subtotal of 80.79
    When I calculate the fee
    Then The fee should be 4.85

    Given I have an order with a total of 75.00 and a subtotal of 60.00
    When I calculate the fee
    Then The fee should be 3.60

    Given I have an order with a total of 74.99 and a subtotal of 59.99
    When I calculate the fee
    Then i should get an error with this "Total must be greater than 74.99" message

  Scenario:  order total is greater than 100.99 and less than 501 -- 5% fee
    Given I have an order with a total of 101.00 and a subtotal of 80.80
    When I calculate the fee
    Then The fee should be 4.04

    Given I have an order with a total of 500.99 and a subtotal of 400.79
    When I calculate the fee
    Then The fee should be 20.04


  Scenario:  order total is greater than 500.99 and less than 1000.01 -- 4% fee
    Given I have an order with a total of 1000.00 and a subtotal of 800.00
    When I calculate the fee
    Then The fee should be 32.00

    Given I have an order with a total of 650.00 and a subtotal of 520.00
    When I calculate the fee
    Then The fee should be 20.80

    Given I have an order with a total of 600.00 and a subtotal of 480.00
    When I calculate the fee
    Then The fee should be 19.20

  Scenario: order total is greater than 1000 -- 3% fee
    Given I have an order with a total of 1001.00 and a subtotal of 800.80
    When I calculate the fee
    Then The fee should be 24.02

    Given I have an order with a total of 1000.01 and a subtotal of 800.00
    When I calculate the fee
    Then The fee should be 24.00

    Given I have an order with a total of 8000.00 and a subtotal of 6400.00
    When I calculate the fee
    Then The fee should be 192.00






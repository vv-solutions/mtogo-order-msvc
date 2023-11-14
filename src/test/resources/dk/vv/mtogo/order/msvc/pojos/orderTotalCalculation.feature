Feature: Calculate order total
  Scenario: Orderline totals are positive integers
    Given I have an order with the following orderline totals:
      |100.00|
      |120.00|
      |180.00|
    When I calculate the order total
    Then The order total should be 400.00

    Given I have an order with the following orderline totals:
      |100.00|
    When I calculate the order total
    Then The order total should be 100.00

    Given I have an order with the following orderline totals:
      |100.00|
      |120.00|
      |91.00|
      |30.00|
      |130.00|
      |180.00|
      |110.00|
      |150.00|
      |138.00|
      |120.00|
      |90.00|
      |55.00|
      |153.00|
      |112.00|
      |117.00|
      |32.00|
      |65.00|
      |11.00|
    When I calculate the order total
    Then The order total should be 1804.00

  Scenario: Orderline totals are floating point numbers
    Given I have an order with the following orderline totals:
      |100.70|
      |120.86|
      |1.01|
      |19.01|
    When I calculate the order total
    Then The order total should be 241.58

    Given I have an order with the following orderline totals:
      |100.70|
    When I calculate the order total
    Then The order total should be 100.70

  Scenario: Orderline totals are a mix of integers and floating point numbers
    Given I have an order with the following orderline totals:
      |100.70|
      |120.00|
      |1.01|
    When I calculate the order total
    Then The order total should be 221.71

  Scenario: Bad input
    Given I have an order with the following orderline totals:
      |100.70|
      |120.00|
      |-1.01|
    When I calculate the order total
    Then Then i should get an error message saying this "Totals must be greater than zero"

    Given I have an order with the following orderline totals:
      |-100.00|
      |-120.00|
      |-1.00|
    When I calculate the order total
    Then Then i should get an error message saying this "Totals must be greater than zero"

  Scenario: Totals are zero
    Given I have an order with the following orderline totals:
      |0.00|
      |0.00|
      |0.00|
    When I calculate the order total
    Then The order total should be 0.00

    Given I have an order with the following orderline totals:
      |0.00|
    When I calculate the order total
    Then The order total should be 0.00
    

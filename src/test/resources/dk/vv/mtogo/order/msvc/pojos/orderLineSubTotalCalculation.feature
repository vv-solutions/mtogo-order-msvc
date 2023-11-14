 Feature: Calculate order line sub total
  Scenario: Prices are positive integers
    Given I have an order line with a quantity of 2 and a unit gross price of 10.00
    When I calculate the sub total
    Then the sub total should be 20.00

    Given I have an order line with a quantity of 2 and a unit gross price of 50.00
    When I calculate the sub total
    Then the sub total should be 100.00

    Scenario: Prices are positive decimals
      Given I have an order line with a quantity of 2 and a unit gross price of 10.70
      When I calculate the sub total
      Then the sub total should be 21.40

      Given I have an order line with a quantity of 9 and a unit gross price of 50.30
      When I calculate the sub total
      Then the sub total should be 452.70

    Scenario: Bad input value
      Given I have an order line with a quantity of 2 and a unit gross price of -10.70
      When I calculate the sub total
      Then I should get this error message "Unit gross price must be zero or greater"

      Given I have an order line with a quantity of -2 and a unit gross price of 10.70
      When I calculate the sub total
      Then I should get this error message "Quantity must be zero or greater"

    Scenario: Gross price is 0
      Given I have an order line with a quantity of 2 and a unit gross price of 0.00
      When I calculate the sub total
      Then the sub total should be 0.00

    Scenario: Quantity is 0
      Given I have an order line with a quantity of 0 and a unit gross price of 10.70
      When I calculate the sub total
      Then the sub total should be 0.00

    Scenario: Gross price is 0 and quantity is 0
      Given I have an order line with a quantity of 0 and a unit gross price of 0.00
      When I calculate the sub total
      Then the sub total should be 0.00


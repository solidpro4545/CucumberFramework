Feature: Login and cart

  @login
  Scenario: Successful login
    Given I am on the login page
    When I enter a valid username and password
    Then I should be redirected to the dashboard
    And I should see "Swag Labs" at the top of the page

  @cart
  Scenario: Add a product to the cart and verify it
    Given I am on the login page
    When I enter a valid username and password
    Then I should be redirected to the dashboard
    And I should see "Swag Labs" at the top of the page
    When I add "Sauce Labs Backpack" to the cart
    And I open the cart
    Then I should see "Sauce Labs Backpack" in the cart
    And the cart badge should show "1"

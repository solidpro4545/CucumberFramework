Feature: Login

  Scenario: Successful login
    Given I am on the login page
    When I enter a valid username and password
    Then I should be redirected to the dashboard
    And I should see "Swag Labs" at the top of the page
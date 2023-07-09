Feature: Login page Tests

  Scenario: Login to the Trello page with correct credentials
    Given I navigate to the Login page of Trello
    When I enter the correct credentials and submit
    Then I am logged in the home page
Feature: Scenarios for Creating a Board
  Background: User is logged in
    Given I navigate to the Login page of Trello
    When I enter the correct credentials and submit

 Scenario: Create a board from the home page using the Create button
    Given I am logged in the home page
    When I click the Create button to create a new board
    And I enter the board name "MyFirstTrelloBoard" and submit
    Then a new board is created successfully

  Scenario: Edit a board to add a new card
   Given I have opened the Trello board page
   When I edit a board to add a new card
   Then the card is added and board is updated

  Scenario: Close the board from the menu
    Given I have opened the Trello board page
    When I click the menu options and close the board
    Then the board is closed successfully

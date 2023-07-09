Feature: Test the Trello Board end to end

  Scenario: Create a Trello Board using the backend API
    When a request is made to create a new Trello board "TrelloBoard1" using the endpoint
    Then the board is created successfully

  Scenario: Fetch the details of the board that was created
    When the details of the board are fetched then they are returned

  Scenario: Edit the details of a Trello Board created
    When a request is made to update the name of the Trello board
    Then the board details are updated

  Scenario: Delete the Trello board that was created
    When a request is made to delete the Trello board
    Then the board is deleted

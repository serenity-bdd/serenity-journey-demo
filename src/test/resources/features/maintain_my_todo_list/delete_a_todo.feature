Feature: Delete a todo
  I need to be able to delete a todo item if I made a mistake or no longer need to do it.

  Scenario: Delete an active todo
    Given Jane has a todo list containing Buy some milk, Buy Petrol
    When she deletes the todo action Buy some milk
    Then her todo list should contain Buy Petrol

  Scenario: Delete a completed todo
    Given Joe has a todo list containing Buy some milk,Buy Petrol
    And he has marked the Buy some milk action as complete
    When he deletes the todo action Buy some milk
    Then his todo list should contain Buy Petrol

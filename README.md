# Serenity Exercise

This exercise will introduce you to the [Serenity library](http://www.thucydides.info/) and let you experiment with
writing acceptance tests exercising a simple [To-do List App](http://todomvc.com/examples/angularjs/#/)
using [Cucumber](https://cucumber.io/).

## Pre-requesites

To follow this exercise, you will need to install the following on your machine:
- JDK 1.7
- Maven 3.x
- Git
- A Java IDE (IntelliJ or Eclipse)
- The Cucumber plugin for whatever Java IDE you are using

## Setting up the project

1. Clone the project from Github and checkout the `serenity-test` branch:

    ```console
    git clone git@github.com:serenity-bdd/serenity-journey-demo.git
    git checkout serenity-test
    ```
    
2. Run the project using Maven from the command line to download the dependencies:

    ```console
    mvn clean verify
    ```

    There should be **9 pending** and **1 failing** test

3. Have a look at the test report generated in the following directory:

    `target/site/serenity`

## Part 1: The "Page Objects" approach

### Exercise 1 - Review the existing implementation

1. Review the implementation of the `The number of remaining todos should be visible` scenario in the `add_new_todos.feature`

2. Complete the implementation so that the test passes.

### Exercise 2 - Record a new todo action for future use

Open the `add_new_todos.feature` file to work on the `Record a new todo action for future use scenario`

1. The first step, `I need to buy some milk` is already implemented in the `RecordTodoStepDefinitions` class:

    ```java
    @Given("^I need to (?:.*)$")
    public void i_need_to_add_a_new_task() throws Throwable {
        jane.opens_the_todo_application();
    }
    ```

1. The implementation of the second step: `I add the todo action 'Buy some milk'` is missing and you'll need to add it in:

    ```java
    @When("^I add the todo action '(.*)'$")
    public void i_add_the_todo_action(String actionName) throws Throwable {
        jane.adds_an_action_called(actionName);
    }
    ```

1. Next add the `adds_an_action_called()` method in the `ATodoUser` class: 

    ```java
    @Step
    public void adds_an_action_called(String actionName) {
        onTheTodoHomePage.addAnActionCalled(actionName);
    }
    ```

1. In the `TodoPage` class, implement the `addAnActionCalled()` method:

    ```java
    public void addAnActionCalled(String actionName) {
       // TODO: Implement me
    }
    ```

1. Run `mvn clean verify` and review the report in `target/site/serenity`

1. Refactor as appropriate

### Exercise 3 - New todos should be marked as Active

1. Open the `add_new_todos.feature` and work on the `New todos should be marked as Active` scenario

1. Implement the last step of the scenario: `'Buy some milk' should be recorded in the Active items`

1. Run the tests again

1. Refactor as appropriate

## Exercise 4 - Complete a todo action

1. Open the `complete_todos.feature` and work on the `Complete a todo action` scenario

1. In the `CompleteTodoStepDefinitions` class, implement "I mark the 'Buy some milk' action as complete":

1. Run the tests again

1. Refactor as appropriate

## Part 2 - The Journey Pattern

To learn more about the Journey Pattern and its origins check out [this presentation on SlideShare](
http://www.slideshare.net/wakaleo/serenity-and-the-journey-pattern).

### Exercise 5 - Display Completed tasks (the journey pattern)

1. Open the `filter_todos_by_status.feature` and work on the `Display only Active tasks` scenario

1. Review the implementation of the first two steps to see how the Journey pattern is used in these tests.

1. Implement the step definition method for `When Joe consults the Active tasks` step.
   A simple design might use the following layers:
   - Define the Cucumber step definition in the `FilterTodoStepDefinitions` class
   - Create a FilterItems task for this step definition to use to click on a given filter type in the filter bar
   - Create a FilterBar class to isolate the locators for the filter bar web elements

1. Now implement `Joe's todo list should contain Buy Petrol`
   - Define the Cucumber step definition in the `FilterTodoStepDefinitions` class
   - Create a DisplayedItems question class to find the list of todo items displayed on the screen, and use
     this question class to form the assertion in the step definition.

### Exercise 6

1. Open the `delete_a_todo.feature` file

1. Implement the missing step definition using the Journey pattern.

### Exercise 7

1. Specify and implement a test scenario for a requirement that states that todo items should be displayed in chronological order.

### Exercise 8

1. Refactor the initial tests using the Journey pattern.

That's it! Thanks for your time :-)

We hope you enjoyed it,

The Serenity Team
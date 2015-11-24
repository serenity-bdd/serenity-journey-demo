# Serenity Tutorial

A simple demonstration project using Serenity with Cucumber and JUnit, running tests against the http://todomvc.com/examples/angularjs/#/ application.

The project runs using JDK 1.8 and Maven. To run the demo, run:

```
mvn clean verify
```

The Serenity reports will be generated in the `target/site/serenity` directory.

Below are some exercises to learn to use the library.

## Pre-requesites

To follow this this tutorial, you will need to install the following on your machine:
- JDK 8
- Maven 3.x
- Git
- A Java IDE (IntelliJ or Eclipse)
- The Cucumber plugin for whatever Java IDE you are using

## Setting up the project

Clone the project from Github and checkout the `tutorial` branch:

    $ git clone git@github.com:serenity-bdd/serenity-journey-demo.git
    $ git checkout tutorial
    
Run the project using Maven from the command line to download the dependencies:

    $ mvn clean verify


### Exercise 1 - Record a new todo action for future use

1. Open the `add_new_todos.feature` file to work on the `Record a new todo action for future use` scenario. Implement the `I need to buy some milk` method:

        @Given("^I need to (?:.*)$")
        public void i_need_to_add_a_new_task() throws Throwable {
            jane.opens_the_todo_application();
        }

2. In the `RecordTodoStepDefinitions` class, implement the “When I add the todo action”  step definition

        @When("^I (?:add|have added) the todo action '(.*)'$")
        public void i_add_the_todo_action(String actionName) throws Throwable {
            jane.adds_an_action_called(actionName);
        }

3. In the `ATodoUser` class, add the `adds_an_action_called()` method: 

        @Step
        public void adds_an_action_called(String actionName) {
            onTheTodoHomePage.addAnActionCalled(actionName);
        }

4. In the `TodoHomePage` class, implement the `addAnActionCalled()` method:

        public void addAnActionCalled(String actionName) {
            $("#new-todo").type(actionName)
                          .then().sendKeys(Keys.ENTER);
        }

5. In the `RecordTodoStepDefinitions` class, implement “Should appear in my todo list” 

        @Then("^'(.*)' should (?:appear|be recorded) in my todo list$")
        public void action_should_appear_in_my_todo_list(String action) throws Throwable {
            jane.should_see_the_todo_action(action)
        }

6. In the `ATodoUser` class, implement the `should_see_the_todo_action()` method:

        @Step
        public void should_see_the_todo_action(String action) {
            assertThat(onTheTodoHomePage.getActions()).contains(action);
        }

7. In the `TodoPage` class, implement `getActions()`:

        public List<String> getActions() {
            return findAll(".view").stream()
                                   .map(WebElementFacade::getText)
                                   .collect(Collectors.toList());
        }

8. Run ```mvn clean verify``` and view the report in ```target/site/serenity```

### Exercise 2 - New todos should be marked as Active

1. Open the `add_new_todos.feature` and work on the `New todos should be marked as Active` scenario

2. In the `RecordTodoStepDefinitions` class, implement "'Buy some milk' should be recorded in the Active items" step definition:

        @Then("^'(.*)' should (?:appear|be recorded) in the (.*) items$")
        public void action_should_appear_the_items_of_status(String action, TodoStatusFilter status) throws Throwable {
            jane.filters_by_status(status);
            jane.should_see_the_todo_action(action);
        }

3. In the `ATodoUser` class, implement `filters_by_status()`:

        @Step
        public void filters_by_status(TodoStatusFilter status) {
            onTheTodoHomePage.filterByStatus(status);
        }

4. In the `TodoPage`, implement `filterByStatus()`:

        public void filterByStatus(TodoStatusFilter status) {
             findBy("#filters")
                     .then().findBy(statusFilterLinkFor(status))
                     .then().click();
         }

         private String statusFilterLinkFor(TodoStatusFilter status) {
             return String.format(".//a[.='%s']", status.name());
                   }

5. In the `ATodoUser`, implement should_see_the_todo_action()

        @Step
        public void should_see_the_todo_actions(String... actionNames) {
            assertThat(onTheTodoHomePage.getActions()).containsExactly(actionNames);
        }

6. In the `TodoPage` class, implement `getActions()`:

        public List<String> getActions() {
            return findAll(".view").stream()
                                   .map(WebElementFacade::getText)
                                   .collect(Collectors.toList());
        }

7. Run the tests again

## Exercise 3 - Complete a todo action


1. Open the `complete_todos.feature` and work on the `Complete a todo action` scenario

2. In the `CompleteTodoStepDefinitions` class, implement "I mark the 'Buy some milk' action as complete":

        @When("^I mark the '(.*)' action as complete$")
        public void i_mark_the_action_as_complete(String action) throws Throwable {
            todoPage.markComplete(action);
        }

3. In the `TodoPage` class, implement markComplete():

         public static final String COMPLETE_TICKBOX = ".//input[@ng-model='todo.completed']";

         public void markComplete(String action) {
             inActionRowFor(action).findBy(COMPLETE_TICKBOX).click();
         }

4. In the `CompleteTodoStepDefinitions` class, implement "Then 'Buy some milk' should appear as completed":

        @Then("^'(.*)' should appear as completed$")
        public void should_appear_as_completed(String action) throws Throwable {
            assertThat(todoPage.getStatusFor(action)).isEqualTo(TodoStatus.Completed);
        }

5. In the `TodoPage` class, implement getStatusFor():

        public static final String ACTION_ROW = "//div[@class='view' and contains(.,'%s')]";

        private WebElementFacade inActionRowFor(String action) {
            return $(String.format(ACTION_ROW, action));
        }

        public TodoStatus getStatusFor(String action) {
            WebElementFacade actionRow = inActionRowFor(action);
            return isShownAsCompleted(actionRow) ? TodoStatus.Completed : TodoStatus.Active;
        }

        private boolean isShownAsCompleted(WebElementFacade actionRow) {
            return actionRow.find(By.tagName("label")).getCssValue("text-decoration").equals("line-through");
        }


## Part 2 - The Journey Pattern

### Exercise 4 - Display Completed tasks (the journey pattern)

1. Open the `filter_todos_by_status.feature` and work on the `Display only Active tasks` scenario

1. Review the implementation of the first two steps to see how the Journey pattern is used in these tests.

1. Implement the step definition method for `When Joe consults the Active tasks` step.
A simple design might use the following layers:
   - Define the Cucumber step definition in the `FilterTodoStepDefinitions` class
   - Create a FilterItems task for this step definition to use to click on a given filter type in the filter bar
   - Create a FilterBsr class to isolate the locators for the filter bar web elements

1. Now implement `Joe's todo list should contain Buy Petrol`
   - Define the Cucumber step definition in the `FilterTodoStepDefinitions` class
   - Create a DisplayedItems question class to find the list of todo items displayed on the screen, and use
   this question class to form the assertion in the step definition.

### Exercise 5

1. Open the `delete_a_todo,feature` file

1. Implement the missing step definition using the Journey pattern.
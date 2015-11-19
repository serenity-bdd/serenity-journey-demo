# Serenity Tutorial

A simple demonstration project using Serenity with Cucumber and JUnit, running tests against the http://todomvc.com/examples/angularjs/#/ application.

The project runs using JDK 1.8 and Maven. To run the demo, run:

```
mvn clean verify
```

The Serenity reports will be generated in the `target/site/serenity` directory.

Below are some exercises to learn to use the library:

## Part 1 - Classic TDD

### Exercise 1 - Record a new todo action for future use

1. Open the `add_new_todos.feature` and work on the `Record a new todo action for future use` scenario.
Implement the `I need to...` method:

        @Given("^I need to (?:.*)$")
        public void i_need_to_add_a_new_task() throws Throwable {
            jane.opens_the_todo_application();
        }

2. Implement “When I add the todo action”      

        jane.adds_an_action_called(actionName)```

3. In ATodoUser add: 

        @Step
        public void adds_an_action_called(String actionName) {
            onTheTodoHomePage.addAnActionCalled(actionName);
        }
    
4. In the TodoHomePage, add:

        public void addAnActionCalled(String actionName) {
            $("#new-todo").type(actionName)
                          .then().sendKeys(Keys.ENTER);
        }

5. Implement “Should appear in my todo list”      

        jane.should_see_the_todo_action(action)
    
6. In ATodoUser, add:

        @Step
        public void should_see_the_todo_action(String action) {
            assertThat(onTheTodoHomePage.getActions()).contains(action);
        }
    
7. In the TodoHomePage, add:

        public List<String> getActions() {
            return findAll(".view").stream()
                                   .map(WebElementFacade::getText)
                                   .collect(Collectors.toList());
        }

8. Run ```mvn clean verify``` and view the report in ```target/site/serenity``` 

### Exercise 2 - New todos should be marked as Active

1. Open the `add_new_todos.feature` and work on the `New todos should be marked as Active` scenario

2. Implement "'Buy some milk' should be recorded in the Active items":

        jane.filters_by_status(status);
        jane.should_see_the_todo_action(action);

3. Implement filters_by_status():

        @Step
        public void filters_by_status(TodoStatusFilter status) {
            onTheTodoHomePage.filterByStatus(status);
        }

4. Implement filterByStatus():

        public void filterByStatus(TodoStatusFilter status) {
             findBy("#filters")
                     .then().findBy(statusFilterLinkFor(status))
                     .then().click();
         }
     
         private String statusFilterLinkFor(TodoStatusFilter status) {
             return String.format(".//a[.='%s']", status.name());
         }
     
5. Implement should_see_the_todo_action()
    
        @Step
        public void should_see_the_todo_actions(String... actionNames) {
            assertThat(onTheTodoHomePage.getActions()).containsExactly(actionNames);
        }

6. Implement getActions():

        public List<String> getActions() {
            return findAll(".view").stream()
                                   .map(WebElementFacade::getText)
                                   .collect(Collectors.toList());
        }
    
7. Run the tests again

## Exercise 3 - Complete a todo action

1. Open the `complete_todos.feature` and work on the `Complete a todo action` scenario

2. Implement "I mark the 'Buy some milk' action as complete":

        todoPage.markComplete(action);

3. Implement markComplete():
 
         public static final String COMPLETE_TICKBOX = ".//input[@ng-model='todo.completed']";
     
         public void markComplete(String action) {
             inActionRowFor(action).findBy(COMPLETE_TICKBOX).click();
         }

4. Implement "Then 'Buy some milk' should appear as completed":

        assertThat(todoPage.getStatusFor(action)).isEqualTo(TodoStatus.Completed);

5. Implement getStatusFor():

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

2. Review the implementation of the first two steps.

3. Implement the `When Joe consults the Active tasks` step:

        theActorNamed(name).attemptsTo(FilterItems.byStatus(status));
        
4. Create a FilterItems task:

        public class FilterItems implements Performable {
        
            final TodoStatusFilter filter;
        
            public FilterItems(TodoStatusFilter filter) {
                this.filter = filter;
            }
        
            @Step("{0} filters items by status #status")
            public <T extends Actor> void performAs(T theActor) {
                theActor.attemptsTo(Click.on(FilterBar.filterCalled(filter.name())));
            }
        
            public static FilterItems byStatus(TodoStatusFilter status) {
                return instrumented(FilterItems.class, status);
            }
        }
        
5. Implement the FilterBar class:

        public class FilterBar {
            public static final Target CLEAR_COMPLETED = Target.the("Clear completed button").locatedBy("#clear-completed");
        
            public static Target filterCalled(String name) {
                String FILTER_BUTTON = "//a[.='%s']";
                return Target.the(name + " filter").locatedBy(String.format(FILTER_BUTTON, name));
            }
        }

6. Implement `Joe's todo list should contain Buy Petrol`

        @Then("^(.*)'s todo list should contain (.*)$")
        public void my_todo_list_should_contain(String actor, List<String> expectedTodos) throws Throwable {
            theActorNamed(actor).should(seeThat(theDisplayedItems, containsInAnyOrder(theActionsIn(expectedTodos)));
        }
    
        private Object[] theActionsIn(List<String> expectedTodos) {
            return expectedTodos.toArray();
        }

7. Implement the DisplayedItems question class

        @Steps DisplayedItems theDisplayedItems;

        public class DisplayedItems implements Question<List<String>> {
        
            public static DisplayedItems theDisplayedItems() {
                return new DisplayedItems();
            }
        
            @Override
            public List<String> answeredBy(Actor actor) {
                return BrowseTheWeb.as(actor).findAll(ToDoList.TODO_ITEMS).stream()
                                             .map(WebElementFacade::getText)
                                             .collect(Collectors.toList());
            }
        }
    
8. Rerun the tests

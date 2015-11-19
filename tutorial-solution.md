#Serenity Tutorial - Solution

## Part 1 - Classic TDD

### Exercise 1 - Record a new todo action for future use

1) Open the `add_new_todos.feature`

2) Implement “When I add the todo action”      

```jane.adds_an_action_called(actionName)```

3) In ATodoUser add: 

    @Step
    public void adds_an_action_called(String actionName) {
        onTheTodoHomePage.addAnActionCalled(actionName);
    }
    
4) In the TodoHomePage, add:

    public void addAnActionCalled(String actionName) {
        $("#new-todo").type(actionName)
                      .then().sendKeys(Keys.ENTER);
    }

5) Implement “Should appear in my todo list”      

    jane.should_see_the_todo_action(action)
    
6) In ATodoUser, add:

    @Step
    public void should_see_the_todo_action(String action) {
        assertThat(onTheTodoHomePage.getActions()).contains(action);
    }
    
7) In the TodoHomePage, add:

    public List<String> getActions() {
        return findAll(".view").stream()
                               .map(WebElementFacade::getText)
                               .collect(Collectors.toList());
    }

8) Run ```mvn clean verify``` and view the report in ```target/site/serenity``` 

### Exercise 2 - New todos should be marked as Active

1) Implement "'Buy some milk' should be recorded in the Active items":

    jane.filters_by_status(status);
    jane.should_see_the_todo_action(action);

2) Implement filters_by_status():

    @Step
    public void filters_by_status(TodoStatusFilter status) {
        onTheTodoHomePage.filterByStatus(status);
    }

3) Implement filterByStatus():

    public void filterByStatus(TodoStatusFilter status) {
         findBy("#filters")
                 .then().findBy(statusFilterLinkFor(status))
                 .then().click();
     }
 
     private String statusFilterLinkFor(TodoStatusFilter status) {
         return String.format(".//a[.='%s']", status.name());
     }
     
4) Implement should_see_the_todo_action()

    @Step
    public void should_see_the_todo_actions(String... actionNames) {
        assertThat(onTheTodoHomePage.getActions()).containsExactly(actionNames);
    }

5) Implement getActions():

    public List<String> getActions() {
        return findAll(".view").stream()
                               .map(WebElementFacade::getText)
                               .collect(Collectors.toList());
    }
    
6) Run the tests again

## Exercise 3 - Complete a todo action

1) Implement "I mark the 'Buy some milk' action as complete":

    todoPage.markComplete(action);

2) Implement markComplete():
 
     public static final String COMPLETE_TICKBOX = ".//input[@ng-model='todo.completed']";
 
     public void markComplete(String action) {
         inActionRowFor(action).findBy(COMPLETE_TICKBOX).click();
     }

3) Implement "Then 'Buy some milk' should appear as completed":

    assertThat(todoPage.getStatusFor(action)).isEqualTo(TodoStatus.Completed);

4) Implement getStatusFor():

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

### Exercise 4 - Display Completed tasks (the journey pattern)

1) Implement "Display Completed tasks" - "Joe consults the Completed tasks"

    theActorNamed(name).attemptsTo(FilterItems.byStatus(status));

2) Create a FilterItems task:

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




package net.serenitybdd.demos.todos.cucumber.stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.tasks.FilterItems;

import static net.serenitybdd.demos.todos.model.Actors.theActorNamed;

public class FilterTodoStepDefinitions {

    @When("^(.*) consults(?: the)? (.*) tasks$")
    public void consults_a_task_of_a_given_type(String name, TodoStatusFilter status) throws Throwable {
        throw new PendingException();
    }
}

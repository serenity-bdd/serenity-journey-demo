package net.serenitybdd.demos.todos.cucumber.stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.PendingStepException;
import net.serenitybdd.demos.todos.pages.TodoPage;

public class CompleteTodoStepDefinitions {

    TodoPage todoPage;

    @When("^I mark the '(.*)' action as complete$")
    public void i_mark_the_action_as_complete(String action) throws Throwable {
        throw new PendingException();
    }

    @Then("^'(.*)' should appear as completed$")
    public void should_appear_as_completed(String action) throws Throwable {
        throw new PendingException();
    }
}

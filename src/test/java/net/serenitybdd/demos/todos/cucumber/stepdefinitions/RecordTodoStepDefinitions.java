package net.serenitybdd.demos.todos.cucumber.stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.PendingStepException;
import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.serenity.ATodoUser;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;

public class RecordTodoStepDefinitions {

    @Steps ATodoUser jane;

    @Given("^I need to (?:.*)$")
    public void i_need_to_add_a_new_task() throws Throwable {
        jane.opens_the_todo_application();
    }

    @When("^I (?:add|have added) the todo action '(.*)'$")
    public void i_add_the_todo_action(String actionName) throws Throwable {
        throw new PendingException();
    }

    @Then("^'(.*)' should (?:appear|be recorded) in my todo list$")
    public void action_should_appear_in_my_todo_list(String action) throws Throwable {
        throw new PendingException();
    }

    @Then("^'(.*)' should (?:appear|be recorded) in the (.*) items$")
   public void action_should_appear_the_items_of_status(String action, TodoStatusFilter status) throws Throwable {
        throw new PendingException();
   }
}

package net.serenitybdd.demos.todos.cucumber.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.demos.todos.serenity.ATodoUser;
import net.thucydides.core.annotations.Steps;

public class RecordTodoStepDefinitions {

    @Steps ATodoUser jane;

    @Given("^I need to (?:.*)$")
    public void i_need_to_add_a_new_task() throws Throwable {
        jane.opens_the_todo_application();
    }

    @Given("^I have added the todo action '(.*)'$")
    public void i_have_added_the_todo_action(String actionName) throws Throwable {
        jane.has_added_actions_called(actionName);
    }

    @Then("^'(.*)' should be recorded in my todo list$")
    public void action_should_be_recorded_in_my_todo_list(String action) throws Throwable {
        jane.should_see_the_todo_action(action);
    }

    @When("I consult my todo list")
    public void consult_todo_list() {
        jane.opens_the_todo_application();
    }

    @Then("I should have (.*) items left to do")
    public void should_see_remaining_todo_count(int count) {
        jane.should_see_remaining_todo_count_of(count);
    }
}
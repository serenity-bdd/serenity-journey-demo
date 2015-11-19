package net.serenitybdd.demos.todos.cucumber.stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;

import java.util.List;

public class ViewingTodoStepDefinitons {

    @Then("^(.*)'s todo list should contain (.*)$")
    public void my_todo_list_should_contain(String actor, List<String> expectedTodos) throws Throwable {
        throw new PendingException();
    }

}

package net.serenitybdd.demos.todos.features.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.demos.todos.CompleteItem;
import net.serenitybdd.demos.todos.DisplayedItems;
import net.serenitybdd.demos.todos.model.Actors;
import net.serenitybdd.demos.todos.tasks.AddItems;
import net.serenitybdd.demos.todos.tasks.DeleteAnItem;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;

import java.util.Collection;
import java.util.List;

import static net.serenitybdd.demos.todos.model.Actors.theActorNamed;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class DeleteTodoStepDefinitions {


    @Managed
    WebDriver aBrowser;

    @Steps AddItems addSomeItems;
    @Steps DisplayedItems theDisplayedItems;

    Actor theUser;

    @Given("^(.*) has a todo list containing (.*)$")
    public void i_have_a_todo_list_containing(String userName, List<String> thingsToDo) throws Throwable {
        theUser = theActorNamed(userName);
        theUser.can(BrowseTheWeb.with(aBrowser));

        theUser.attemptsTo(addSomeItems.called(thingsToDo));
    }

    @When("^(?:he|she) deletes the todo action (.*)$")
    public void i_delete_the_todo_action(String action) throws Throwable {
        theUser.attemptsTo(DeleteAnItem.called(action));
    }

    @Then("^(?:his|her) todo list should contain (.*)$")
    public void my_todo_list_should_contain(List<String> expectedTodos) throws Throwable {
        theUser.should(seeThat(theDisplayedItems, containsInAnyOrder(theActionsIn(expectedTodos))));
    }

    private Object[] theActionsIn(List<String> expectedTodos) {
        return expectedTodos.toArray();
    }

    @Given("^(?:he|she) has marked the (.*) action as complete$")
    public void i_have_marked_the_action_as_complete(String itemName) throws Throwable {
        theUser.attemptsTo(CompleteItem.called(itemName));
    }
}

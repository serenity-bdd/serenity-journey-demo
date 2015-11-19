package net.serenitybdd.demos.todos.cucumber.stepdefinitions;

import cucumber.api.Pending;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.IgnoredStepException;
import net.serenitybdd.demos.todos.model.ExpectedItemsNotDisplayed;
import net.serenitybdd.demos.todos.tasks.AddItems;
import net.serenitybdd.demos.todos.tasks.CompleteItem;
import net.serenitybdd.demos.todos.tasks.DeleteAnItem;
import net.serenitybdd.demos.todos.tasks.DisplayedItems;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static net.serenitybdd.demos.todos.model.Actors.theActorNamed;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class DeleteTodoStepDefinitions {

    @Managed
    WebDriver janesBrowser;

    @Managed
    WebDriver joesBrowser;

    @Steps AddItems addSomeItems;

    @Given("^(.*) has a todo list containing (.*)$")
    public void has_a_todo_list_containing(String actor, List<String> thingsToDo) throws Throwable {
        theActorNamed(actor).can(BrowseTheWeb.with(theBrowserBelowingTo(actor)));
        theActorNamed(actor).attemptsTo(addSomeItems.called(thingsToDo));
    }


    @When("^(.*) deletes the todo action (.*)$")
    public void delete_the_todo_action(String actor, String action) throws Throwable {
        theActorNamed(actor).attemptsTo(DeleteAnItem.called(action));
    }

    @Given("^(.*) has marked the (.*) action as complete$")
    public void i_have_marked_the_action_as_complete(String actor, String itemName) throws Throwable {
        theActorNamed(actor).attemptsTo(CompleteItem.called(itemName));
    }


    private WebDriver theBrowserBelowingTo(String actor) {
        switch (actor) {
            case "Jane" :
                return janesBrowser;
            case "Joe" :
                return joesBrowser;
            default:
                return janesBrowser;
        }
    }

}

package net.serenitybdd.demos.todos.features.maintain_my_todo_list;

import net.serenitybdd.demos.todos.tasks.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;

/**
 * This example illustrates using the Journey pattern with JUnit.
 */
@RunWith(SerenityRunner.class)
public class TidyUpTheTodoListWithFailures {

    @Managed
    WebDriver hisBrowser;

    @Steps AddItems addedSomeItems;
    @Steps DisplayedItems theDisplayedItems;
    @Steps ClearCompletedItems clearTheCompletedItems;

    Actor joe = Actor.named("Joe");

    @Before
    public void joe_can_use_a_browser() {
        joe.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void should_be_able_to_remove_cat_related_completed_items_from_the_todo_list() {

        givenThat(joe).has(addedSomeItems.called("Buy the milk", "Walk the dog"));

        when(joe).attemptsTo(
                CompleteItem.called("Buy the milk"),
                clearTheCompletedItems
        );

        then(joe).should(seeThat(theDisplayedItems, contains("Walk the cat")));

    }

    @Test
    public void should_be_able_to_remove_dog_related_completed_items_from_the_todo_list() {

        givenThat(joe).has(addedSomeItems.called("Buy the milk", "Walk the dog"));

        when(joe).attemptsTo(
                FailBecause.theUIHasBeenChanged(),
                CompleteItem.called("Buy the milk"),
                clearTheCompletedItems
        );

        then(joe).should(seeThat(theDisplayedItems, contains("Walk the dog")));

    }
    @Test
    public void should_be_able_to_remove_dog_when_the_mainframe_is_down() {

        givenThat(joe).has(addedSomeItems.called("Buy the milk", "Walk the dog"));

        when(joe).attemptsTo(
                FailBecause.theMainframeIsDown(),
                CompleteItem.called("Buy the milk"),
                clearTheCompletedItems
        );

        then(joe).should(seeThat(theDisplayedItems, contains("Walk the dog")));

    }

    @Test
    public void should_be_able_to_remove_dog_when_the_mainframe_is_down_at_the_end_of_the_test() {

        givenThat(joe).has(addedSomeItems.called("Buy the milk", "Walk the dog"));

        when(joe).attemptsTo(
                CompleteItem.called("Buy the milk"),
                clearTheCompletedItems,
                FailBecause.theMainframeIsDown()
        );

        then(joe).should(seeThat(theDisplayedItems, contains("Walk the dog")));

    }

    @Test
    public void a_step_failure() {
        addedSomeItems.failedAssert();
    }

    @Test
    public void a_step_error() {
        addedSomeItems.withError();
    }

    @Test
    public void a_compromised_step() {
        addedSomeItems.compromised();
    }

}

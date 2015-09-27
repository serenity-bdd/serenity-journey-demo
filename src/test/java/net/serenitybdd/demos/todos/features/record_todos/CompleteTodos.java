package net.serenitybdd.demos.todos.features.record_todos;

import net.serenitybdd.demos.todos.serenity.ATodoUser;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * This example illustrates using Serenity Steps with JUnit.
 */
@RunWith(SerenityRunner.class)
public class CompleteTodos {

    @Managed
    WebDriver joesBrowser;

    @Steps
    ATodoUser joe;

    @Before
    public void openTheApplication() {
        joe.opensTheTodoApplication();
    }

    @Test
    public void shouldBeAbleToClearCompletedActionsFromTheTodoList() {

        // GIVEN
        joe.hasAddedActionsCalled("Walk the dog", "Put out the garbage");

        // WHEN
        joe.completesTheActionCalled("Walk the dog");
        joe.clearsTheCompletedActions();

        // THEN
        joe.shouldSeeTheTodoActions("Put out the garbage");
    }

}

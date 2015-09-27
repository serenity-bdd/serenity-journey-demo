package net.serenitybdd.demos.todos.serenity;

import com.google.common.collect.ImmutableList;
import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.pages.TodoPage;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by john on 26/09/2015.
 */
public class ATodoUser {

    TodoPage todoPage;

    @Step
    public void opensTheTodoApplication() {
        todoPage.open();
    }

    @Step
    public void addsANewActionCalled(String actionName) {
        todoPage.addActionCalled(actionName);
    }

    @Step
    public void hasAddedActionsCalled(String... actionNames) {
        ImmutableList.copyOf(actionNames).forEach(
                action -> addsANewActionCalled(action)
        );
    }

    @Step
    public void shouldSeeTheTodoAction(String action) {
        assertThat(todoPage.getActions()).contains(action);
    }

    @Step
    public void shouldSeeTheTodoActions(String... actionNames) {
        assertThat(todoPage.getActions()).containsExactly(actionNames);
    }

    @Step
    public void filtersByStatus(TodoStatusFilter status) {
        todoPage.filterByStatus(status);
    }


    @Step("Completes the action called '{0}'")
    public void completesTheActionCalled(String actionName) {
        todoPage.markComplete(actionName);
    }

    @Step
    public void clearsTheCompletedActions() {
        todoPage.clearCompletedActions();
    }
}

package net.serenitybdd.demos.todos.serenity;

import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.pages.TodoPage;
import net.thucydides.core.annotations.Step;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class ATodoUser {


    @Step
    public void opens_the_todo_application() {
        onTheTodoHomePage.open();
    }

    TodoPage onTheTodoHomePage;

    @Step
    public void clears_the_completed_actions() {
        onTheTodoHomePage.clearCompletedActions();
    }
}

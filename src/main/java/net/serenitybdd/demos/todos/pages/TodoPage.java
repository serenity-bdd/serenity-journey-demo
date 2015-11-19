package net.serenitybdd.demos.todos.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.demos.todos.model.TodoStatus;
import net.serenitybdd.demos.todos.model.TodoStatusFilter;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("http://todomvc.com/examples/angularjs/#/")
public class TodoPage extends PageObject {

    public static final String ACTION_ROW_LABEL = "//label[contains(.,'%s')]";

    private WebElementFacade inActionRowLabelFor(String action) {
        return $(String.format(ACTION_ROW_LABEL, action));
    }

    public void clearCompletedActions() {
        $("#clear-completed").click();
    }
}

package net.serenitybdd.demos.todos.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;

import java.util.List;

import static ch.lambdaj.Lambda.extractProperty;

@DefaultUrl("http://todomvc.com/examples/angularjs/#/")
public class TodoPage extends PageObject {

    public static final String ACTION_ROW_LABEL = "//label[contains(.,'%s')]";

    public void addAnActionCalled(String actionName) {
        $("#new-todo").type(actionName)
                .then().sendKeys(Keys.ENTER);
    }

    private WebElementFacade inActionRowLabelFor(String action) {
        return $(String.format(ACTION_ROW_LABEL, action));
    }

    public List<String> getActions() {
        return extractProperty(findAll(".view"),"text");
    }

    public int remainingTodoCount() {
        return 0; // TODO: Make this work
    }
}

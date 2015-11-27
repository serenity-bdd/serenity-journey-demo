package net.serenitybdd.demos.todos.pages.components;

import net.serenitybdd.screenplay.targets.Target;

public class ToDoList {
    public static Target NEW_TODO_FIELD = Target.the("New Todo Field").locatedBy("#new-todo");

    public static Target completeButtonFor(String itemName) {
        String COMPLETE_BUTTON = "//*[@class='view' and contains(.,'%s')]//input[@type='checkbox']";
        return Target.the("Complete button").locatedBy(String.format(COMPLETE_BUTTON, itemName));
    }

}

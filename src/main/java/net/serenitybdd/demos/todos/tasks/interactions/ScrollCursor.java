package net.serenitybdd.demos.todos.tasks.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

public class ScrollCursor implements Performable {

    private Target target;

    public Performable to(String cssOrXpathForElement) {
        this.target = Target.the(cssOrXpathForElement).locatedBy(cssOrXpathForElement);
        return this;
    }

    public Performable to(Target target) {
        this.target = target;
        return this;
    }

    @Step("{0} scrolls to #target")
    public <T extends Actor> void performAs(T theUser) {
        BrowseTheWeb.as(theUser)
                .moveTo(target.getCssOrXPathSelector());
    }
}

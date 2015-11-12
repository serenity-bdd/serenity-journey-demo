package net.serenitybdd.demos.todos.tasks;

public class WrongQuestion extends AssertionError {
    public WrongQuestion(Throwable cause) {
        super(cause);
    }
}

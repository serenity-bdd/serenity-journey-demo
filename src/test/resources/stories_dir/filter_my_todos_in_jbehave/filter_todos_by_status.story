Filter todos by status with JBehave

Narrative: I need to be able show only completed or uncompleted tasks

Meta:
@tag tool:jbehave

Scenario: Display only Active tasks with JBehave
Given Joe has a todo list containing Buy some milk,Buy Petrol
And Joe has marked the Buy some milk action as complete
When Joe consults the Active tasks
Then Joe's todo list should contain Buy Petrol


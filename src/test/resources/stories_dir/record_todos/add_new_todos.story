Add new todos with JBehave

Narrative:
I need to be able to jot down actions I need to do as fast as I think of them

Meta:
@tag tool:jbehave

Scenario: Record a new todo action for future use with JBehave
Given I need to buy some milk
When I add the todo action 'Buy some milk'
Then 'Buy some milk' should be recorded in my todo list

Scenario: New todos should be marked as Active with JBehave
Given I need to buy some milk
When I add the todo action 'Buy some milk'
Then 'Buy some milk' should be recorded in the Active items

Scenario: New todos should also make coffee with JBehave
Given I need to make some coffee
When I add the todo action 'Make some coffee'
Then I should receive a double espresso

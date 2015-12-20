# serenity-journey-demo

A simple demonstration project using Serenity with Cucumber and JUnit, running tests against the http://todomvc.com/examples/angularjs/#/ application.

The project runs using JDK 1.8 and Maven or Gradle. To run the demo, run:

```
mvn clean verify
```
or 

```
gradle clean test aggregate
```

The Serenity reports will be generated in the `target/site/serenity` directory.

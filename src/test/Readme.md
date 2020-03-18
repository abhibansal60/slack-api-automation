#Slack API Automation

A BDD API Automation framework for slack.

#Tech Stack

1. JAVA
2. Cucumber BDD with Java 8 and Junit
3. Spring Dependency Injection
4. Gradle as build tool
5. Rest Assured
6. Cucumber Reporting


#Project Structure

**src/main**: can be used by to create API's

**src/test**: contains code to perform integration/regression tests

**src/test/resources:** contains everything which is not JAVA

**src/test/java:** contains everything which is JAVA

    TestRunner: Entry point for execution
    Channel: Stepdefinition for the Channel.feature file
    RestUtil: All the service call related functions resides here
    APIWorld: A World component used for sharing the objects eithin the scenarios/steps for an execution
    ServiceConfig: Spring Configuration class
    
#Execution Steps

1. Clone the repo
2. Set JAVA_HOME and GRADLE_HOME on your system(if not already done)
3. Open command prompt or terminal
4. Run the following command:
    `gradle test`
    





# Test Automation Framework

Created by Tomasz Buga, SDET | https://www.tomaszbuga.pl

GitHub: https://github.com/n4bik

LinkedIn: https://www.linkedin.com/in/tomasz-buga-79933886/

## Project Overview

Test Automation Framework - Reusable template for Java/Selenium frameworks.

## Tech Stack

`Java 17` `Maven` `TestNG` `Selenium` `WebDriverManager` `Apache Commons Lang3` `Log4J2`

## Key Files Description

`pom.xml` - this file contains all the dependencies that are being used by the application (and basically everything for
the building/testing/deploying automation with Java)

`BaseTest.java` - our base test configuration class, that has to be inherited by every other test class

`PageObject.java` - our base Page Object class which contains common methods for an enhanced reusability

## Getting started

### Notes

We're not using `Lombok` in this project, because I wanted to have everything visible and available at hand, but if
you'd like to incorporate it, because you're a more advanced `Java` user - feel free to do so, `Lombok` is a great tool!

### Prerequisites

Java 17 JDK (https://www.oracle.com/java/technologies/downloads/#java17)

Apache Maven (https://maven.apache.org/download.cgi)

### Clone the repository & run the project

Launch `Terminal.app` and navigate (using `cd` command) to directory, where you'd like to store your copy of the source
code

Use the command below to clone the repository to your local directory

```
git clone https://github.com/n4bik/TAF.git
``` 

Navigate (using `cd` command) to the root directory of the project (basically - it's where the `pom.xml` file is
located)

Run the command below in order to run the tests

```
mvn clean test
```

[![Stand With Ukraine](https://raw.githubusercontent.com/vshymanskyy/StandWithUkraine/main/badges/StandWithUkraine.svg)](https://stand-with-ukraine.pp.ua)

# Test Automation Framework

___
Created by Tomasz Buga, SDET | https://www.tomaszbuga.pl

GitHub: https://github.com/n4bik

LinkedIn: https://www.linkedin.com/in/tomasz-buga-79933886/
___

## Project Overview

Test Automation Framework - Reusable template for training purposes of Java/Selenium test automation frameworks.

## 🚀 Features

- End-to-end solution written with the vanilla `Selenium`
- `Allure` library implemented for reports generation - read more in
  the [Allure Reports Generation (Getting Started)](#-allure-reports-generation) section
- By locator finder implementation, for handling infamous `StaleElementException`
- REST API testing implementation (HTTP requests handled by `RestAssured` library)
    - with self-hosted minimalistic API server - read more in the [API Server (Getting Started)](#-api-server) section
- `TestNG` test suites implementation
- `PostgreSQL` database with initial data provided - read more in
  the [PostgreSQL Database (Getting Started)](#-postgresql-database) section

## 💻 Tech Stack

`Java 17` `Maven` `TestNG` `Selenium` `WebDriverManager` `Apache Commons Lang3` `Log4J2` `Lombok`

## 🗂 Key Files Description

[`pom.xml`](pom.xml) - this file contains all the dependencies that are being used by the application (and basically
everything for
the building/testing/deploying automation with Java)

[`BaseTest.java`](src/test/java/pl/tomaszbuga/framework/BaseTest.java) - our base test configuration class, that has to
be inherited by every other test class

[`PageObject.java`](src/test/java/pl/tomaszbuga/framework/PageObject.java) - our base Page Object class, which contains
common methods for an enhanced reusability
___

## Getting started

___

### Prerequisites

Java 17 JDK (https://www.oracle.com/java/technologies/downloads/#java17)

Apache Maven (https://maven.apache.org/download.cgi)
___

### Clone the repository & run the project

Launch `Terminal.app` and navigate (using `cd` command) to directory, where you'd like to store your copy of the source
code

Use the command below to clone the repository to your local directory

```zsh
git clone https://github.com/n4bik/TAF.git
``` 

Navigate (using `cd` command) to the root directory of the project (basically - it's where the `pom.xml` file is
located)

Run the command below in order to run the tests

```zsh
mvn clean test -DsuiteFile=full
```

Available environment variables:

| -             | Available values   | Default value                                                   | Argument example   |
|---------------|--------------------|-----------------------------------------------------------------|--------------------|
| **suiteFile** | ui / api / full    | full                                                            | ` -DsuiteFile=ui ` |
| **groups**    | Smoke / Regression | tests from all groups will be launched if value is not provided | ` -Dgroups=Smoke ` |

___

## How to use bundled features?

___

### 📖 API Server

For training purposes it's an overkill to use an actual backend just to try out API calls.
To keep things simple, we're using the [JSON Server](https://www.npmjs.com/package/json-server).

It requires [NodeJS](https://nodejs.org/en/) in order to work.

Once you have Node installed on your machine, execute the code below.

To install JSON Server on your machine:

```zsh
npm install -g json-server
```

To launch the JSON Server (execute the code from the root directory):

```zsh
json-server --watch ./assets/apiServer/db.json
```

At last, you can launch the API tests:

```zsh
mvn test -DsuiteFile=api
```

#### 👌 Tips

___
Creating POJOs based on JSON schema, to map the API response, can be dreadful. It's much more pleasant to use online generators. For instance: https://www.jsonschema2pojo.org/
___
Use the [Postman](https://www.postman.com/) app when you're designing API calls.

In the [`./assets/apiServer`](assets/apiServer) directory, you can find the Postman's collection. You can import it and
use as a template to get you up to speed quickly.
___
By default there is a `log().all()` method chain implemented to the API calls, but it's commented out. For the debugging, feel free to uncomment these lines.
___
If you'd like to return to the initial state of the database, after playing around with it, simply copy & paste the
content of the [`db_initial.json`](assets/apiServer/db_initial.json) into the [`db.json`](assets/apiServer/db.json).

You can also run this bash command (from the root directory):

```zsh
cd assets/apiServer && cp db_initial.json db.json && cd ../..
```

___

### 📊 Allure Reports Generation

If you'd like to generate a test run report you have to install `Allure` on your machine. Installation manual can be
found
here: [https://docs.qameta.io/allure/#_installing_a_commandline](https://docs.qameta.io/allure/#_installing_a_commandline).

Once, you have installed the Allure, verify that you can run it from the console level:

```zsh
allure --version
```

This command should return the installed Allure version (e.g., 2.20.1)

Okay, so now you have to run whichever test suite. For faster results, you can use the API suite, as it doesn't require
opening a browser.

```zsh
mvn clean test -DsuiteFile=api,
```

If you want to generate static files to host them (e.g., on `GitHub Pages`), then you'd want to use the `generate`
command:

```zsh
allure generate --output ./allureOutput ./allure-results
```

You can now host the entire [`./allureOutput`](./allureOutput) directory, or see the report by opening
the [`index.html`](allureOutput/index.html) file in any browser.

Otherwise, if you'd like to temporary host a Allure server on your machine, use the `serve` command (from the root
directory):

```zsh
allure serve
```

___

### 💾 PostgreSQL Database

`PostgreSQL` is still widely used across different projects. If you'd like to practice `JDBC` or `JPA` APIs you can
quickly spin up a fully functional `PostgreSQL` database using the `docker-compose`.

This approach requires the `Docker engine` installed on your machine. The easiest way to do it, is to install the [Docker Desktop](https://www.docker.com/products/docker-desktop/) app.

You have to run the command from the [`./assets/postgresDatabase`](./assets/postgresDatabase) directory. Run the code below from the project's root directory:

```zsh
cd assets/postgresDatabase && DATABASE_NAME=world docker-compose up
```

Available environment variables:

| -                 | Available values | Default value | Argument example        |
|-------------------|------------------|---------------|-------------------------|
| **DATABASE_NAME** | zoo / world      | zoo           | ` DATABASE_NAME=world ` |

After you're done with playing around with `PostgreSQL` and you'd like to clean it up - kill the ongoing process and use
the Docker's `down` command:

```zsh
cd assets/postgresDatabase && docker-compose down && cd ../..
```

In case you'd like your data to persist, don't use the `down` command, instead just terminate the process and start your
database with the `up` command:

```zsh
cd assets/postgresDatabase && docker-compose up
```

Note:

If you want to switch between databases you have to use the `down` command first. Otherwise, the database will not
update.

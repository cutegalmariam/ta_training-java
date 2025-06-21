# Sauce Demo UI Automation Tests

This repository contains automated UI tests for the [Sauce Demo](https://www.saucedemo.com/) application, implemented using Java, Selenium WebDriver, JUnit 5, and AssertJ. Tests cover the following user scenarios (UC):

* **UC-1: Empty Credentials**
  Attempt to log in with both username and password blank, and verify the appropriate error message.
* **UC-2: Username Only**
  Attempt to log in with only the username provided, leaving the password blank, and verify the error.
* **UC-3: Successful Login**
  Log in with valid credentials (`standard_user` / `secret_sauce`) and verify landing on the dashboard.

## Technologies & Tools

* **Java 17**
* **Maven** for build and dependency management
* **JUnit 5** as the test framework
* **Selenium WebDriver 4** for browser automation
* **WebDriverManager** for automatic driver binaries
* **AssertJ** for fluent assertions
* **SLF4J (slf4j-simple)** for logging

## Project Structure

```
src/
├── main/
│   └── java/
│       ├── com.mycompany.pages/
│       │   ├── LoginPage.java         # Page-object for login screen
│       │   └── DashboardPage.java     # Page-object for main dashboard
│       └── com.mycompany.factory/
│           └── DriverFactory.java     # Thread-local factory for WebDriver instances
└── test/
    └── java/
        └── com.mycompany.tests/
            └── LoginTest.java         # Parameterized JUnit tests for UC-1, UC-2, UC-3
```

## Prerequisites

* JDK 17 installed and configured (`java -version` should report 17.x)
* Maven 3.6+ installed (`mvn -version`)
* Internet access to download dependencies and browser drivers
* Installed browsers: **Firefox** and **Microsoft Edge**

## Setup & Running Tests

1. **Clone the repository**

   ```bash
   git clone https://your.git.host/your-org/sauce-demo-tests.git
   cd sauce-demo-tests
   ```

2. **Run all tests**

   ```bash
   mvn clean test
   ```

3. **Execute a specific scenario or browser**
   Use JUnit tags or filtering (if configured), or temporarily comment out other lines in the CSV.

4. **Parallel execution**
   Tests run in parallel at the method level, leveraging JUnit 5’s parallel-mode and Maven Surefire’s `<parallel>methods` setting.

5. **Logging output**
   Test logs are printed via SLF4J to the console. Adjust the log level in `src/main/resources/simplelogger.properties` if needed.

## Key Patterns & Practices

* **Page Object Model**: Separates page interactions (`LoginPage`, `DashboardPage`) from test logic.
* **Factory Method + ThreadLocal**: `DriverFactory` lazily creates one WebDriver per thread, ensuring safe parallel runs.
* **Parameterized Tests**: JUnit 5’s `@ParameterizedTest` with `@CsvSource` covers multiple UCs and browsers in a single test method.
* **Fluent Assertions**: AssertJ’s `assertThat(...)` yields readable, chainable checks.

---
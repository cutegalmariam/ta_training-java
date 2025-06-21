package com.mycompany.tests;

import com.mycompany.factory.DriverFactory;
import com.mycompany.pages.LoginPage;
import com.mycompany.pages.DashboardPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @BeforeEach
    void setUp(TestInfo info) {
        // ensure a driver is created for this thread
        String browser = info.getDisplayName().toLowerCase();
        WebDriver driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        DriverFactory.cleanupDriver();
    }

    @ParameterizedTest(name = "{index} ⇒ user=''{0}'', pass=''{1}'' on {2}")
    @CsvSource({
            // UC-1
            "'','', firefox",
            "'','', edge",
            // UC-2
            "'standard_user','', firefox",
            "'standard_user','', edge",
            // UC-3
            "'standard_user','secret_sauce', firefox",
            "'standard_user','secret_sauce', edge"
    })
    void loginTests(String user, String pass, String browser) {
        log.info("Running loginTests with user='{}', pass='{}' on {}", user, pass, browser);

        WebDriver driver = DriverFactory.getDriver(browser);
        LoginPage login = new LoginPage(driver);
        login.setUsername(user);
        login.setPassword(pass);
        login.clickLogin();

        if (user.isEmpty()) {
            // UC-1
            assertThat(login.getError())
                    .as("empty‐username error")
                    .contains("Username is required");
        }
        else if (pass.isEmpty()) {
            // UC-2
            assertThat(login.getError())
                    .as("empty‐password error")
                    .contains("Password is required");
        }
        else {
            // UC-3
            DashboardPage dash = new DashboardPage(driver);
            assertThat(dash.getHeaderText())
                    .as("dashboard header text")
                    .isEqualTo("Swag Labs");
        }
    }
}

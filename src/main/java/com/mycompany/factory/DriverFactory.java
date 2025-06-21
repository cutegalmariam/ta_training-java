package com.mycompany.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> THREAD_DRIVER = new ThreadLocal<>();

    // prevent instantiation
    private DriverFactory() {}

    /**
     * returns the WebDriver for this thread creating if necessary.
     * @param browser "firefox" or "edge"
     */
    public static WebDriver getDriver(String browser) {
        WebDriver driver = THREAD_DRIVER.get();
        if (driver == null) {
            driver = createDriver(browser);
            THREAD_DRIVER.set(driver);
        }
        return driver;
    }

    /**
     * quits & removes the WebDriver for this thread
     */
    public static void cleanupDriver() {
        WebDriver driver = THREAD_DRIVER.get();
        if (driver != null) {
            driver.quit();
            THREAD_DRIVER.remove();
        }
    }

    // the “factory method” that picks which browser to spin up
    private static WebDriver createDriver(String browser) {
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();

        switch (browser.toLowerCase()) {
            case "edge":
                return new EdgeDriver();
            case "firefox":
            default:
                return new FirefoxDriver();
        }
    }
}


package com.mycompany.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By usernameField = By.xpath("//input[@id='user-name']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By loginButton   = By.xpath("//input[@id='login-button']");
    private final By errorMessage  = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.saucedemo.com/");
    }

    public void setUsername(String u) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(u);
    }

    public void setPassword(String p) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(p);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getError() {
        return driver.findElement(errorMessage).getText();
    }
}
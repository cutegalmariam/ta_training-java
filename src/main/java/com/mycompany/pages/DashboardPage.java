package com.mycompany.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class DashboardPage {
    private final WebDriver driver;
    By header = By.xpath("//div[@class='app_logo']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getHeaderText() {
        return driver.findElement(header).getText();
    }
}
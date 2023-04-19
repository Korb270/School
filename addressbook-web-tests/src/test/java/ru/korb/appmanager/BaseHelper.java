package ru.korb.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class BaseHelper {
    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (isElementPresent(locator)) {
            WebElement element = wd.findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
    }

    protected void select(By locator, String text) {
        if (isElementPresent(locator))
            new Select(wd.findElement(locator)).selectByVisibleText(text);
    }

    public boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}

package ru.korb.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void openContactPage() {
        click(By.linkText("home"));
    }

    public void approveAlert(){
        wd.switchTo().alert().accept();
    }
}

package ru.korb.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.time.Duration;

public class ApplicationManager {
    WebDriver wd;

    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private Browser browser;

    public ApplicationManager(Browser browser) {

        this.browser = browser;
    }

    public void init() {
        if (browser.equals(Browser.CHROME)) {
           // System.setProperty("webdriver.chrome.driver", "C:/Program Files/ChromeDriver/chromedriver.exe");
            wd = new ChromeDriver();
        } else if (browser.equals(Browser.EDGE)) {
            wd = new EdgeDriver();
        } else if (browser.equals(Browser.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wd.get("http://localhost:8080/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}

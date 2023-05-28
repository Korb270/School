package ru.korb.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

    private Browser browser;


    public ApplicationManager(Browser browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


        if (browser.equals(Browser.CHROME)) {
           // System.setProperty("webdriver.chrome.driver", "C:/Program Files/ChromeDriver/chromedriver.exe");
            wd = new ChromeDriver();
        } else if (browser.equals(Browser.EDGE)) {
            wd = new EdgeDriver();
        } else if (browser.equals(Browser.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wd.get(properties.getProperty("web.baseUrl"));
        }


    public void stop() {
        wd.quit();
    }
}
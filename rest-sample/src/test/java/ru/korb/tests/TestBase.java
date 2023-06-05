package ru.korb.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import ru.korb.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(Browser.CHROME);


    public boolean isIssueOpen(int issueId) throws IOException {
        String status = String.valueOf(app.issue().checkStatus(issueId));
        if (status.equals("Open") || status.equals("In Progress")) {
            return true;
        }
        return false;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}

package ru.korb.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import com.google.protobuf.ServiceException;
import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.korb.appmanager.ApplicationManager;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(Browser.CHROME);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        IssueData issue = app.soap().getIssues(issueId);
        String status = issue.getStatus().getName();
        if (status.equals("resolved") || status.equals("closed")){
            return true;
        }
        return false;
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, javax.xml.rpc.ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
}

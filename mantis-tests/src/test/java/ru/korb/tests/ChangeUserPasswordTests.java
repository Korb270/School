package ru.korb.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.korb.appmanager.HttpSession;

import java.io.IOException;

public class ChangeUserPasswordTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangeUserPassword() throws IOException {
        HttpSession session = app.newSession();
        session.login("administrator", "root");

    }

    @AfterMethod(alwaysRun = true)
    public void stoptMailServer(){
        app.mail().stop();
    }
}

package ru.korb.tests;

import org.testng.annotations.Test;
import ru.korb.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

    @Test
    public void testLogin () throws IOException, ServiceException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}

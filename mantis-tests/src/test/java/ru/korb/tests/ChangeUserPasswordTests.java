package ru.korb.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.korb.appmanager.HttpSession;
import ru.korb.model.MailMessage;
import ru.korb.model.UserData;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangeUserPasswordTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangeUserPassword() throws IOException, MessagingException {
        app.admin().adminLogin("administrator", "root");
        long now = System.currentTimeMillis();
        if (app.db().users().size() == 0) {
            String user = String.format("user%s", now);
            String realname = String.format("realname%s", now);
            String email = String.format("user%s@localhost.localdomain", now);
            app.registration().regUserInAdmin(user, realname, email);
        }
        UserData user = app.db().users().iterator().next();
        app.admin().changeUserPass(user);
        String password = String.format("pass%s", now);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        app.registration().finish(confirmationLink, password);
        HttpSession session = app.newSession();
        assertTrue(session.login(user.getUsername(), password));
        assertTrue(session.isLoggedInAs(user.getUsername()));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.get(mailMessages.size()-1);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod(alwaysRun = true)
    public void stoptMailServer(){
        app.mail().stop();
    }
}

package ru.korb.tests;

import org.testng.annotations.Test;
import ru.korb.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class RestTests extends TestBase {


    @Test
    public void testCreateIssue() throws IOException {
        int issueIdB = 404;
        if (isIssueOpen(issueIdB)){
            skipIfNotFixed(issueIdB);
        }
        Set<Issue> oldIssues = app.issue().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = app.issue().createIssue(newIssue);
        Set<Issue> newIssues = app.issue().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}

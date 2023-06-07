package ru.korb.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.korb.model.Issue;
import ru.korb.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition () throws MalformedURLException, com.google.protobuf.ServiceException, ServiceException, RemoteException {
        int issueId = 2;
        if (isIssueOpen(issueId)){
            skipIfNotFixed(issueId);
        }
    }

    @Test
    public void testGetProjects() throws ServiceException, MalformedURLException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getId());
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException, com.google.protobuf.ServiceException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
        System.out.println(issue.getStatus());
    }
}

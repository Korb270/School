package ru.korb.tests;

import org.testng.annotations.Test;

public class ContactDeletionInEditTests extends TestBase{

    @Test
    public void testDeletationInEdit(){
        app.getNavigationHelper().openContactPage();
        app.getContactHelper().initContactEdit();
        app.getContactHelper().deleteContactInEdit();
        app.getNavigationHelper().openContactPage();
    }
}

package ru.korb.tests;

import org.testng.annotations.Test;
import ru.korb.model.ContactData;

public class ContactDeletionInEditTests extends TestBase{

    @Test
    public void testDeletationInEdit(){
        app.getNavigationHelper().openContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Igor", "Vladimirovich", "Rysin", "Korb", "Sber", "Saint-Petersburg", "777-77-77", "8-911-111-22-33", "999-99-99", "korb@yandex.ru", "korb@sber.ru", "3", "March", "1988", "Kudrovo", "123-45-67"));
        }
        app.getContactHelper().initContactEdit();
        app.getContactHelper().deleteContactInEdit();
        app.getNavigationHelper().openContactPage();
    }
}

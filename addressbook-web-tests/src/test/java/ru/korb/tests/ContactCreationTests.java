package ru.korb.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().openContactPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanovich", "Ivanov", "Korb", "Sber", "Saint-Petersburg", "777-77-77", "8-911-111-22-33", "999-99-99", "korb@yandex.ru", "korb@sber.ru", "3", "March", "1988", "Kudrovo", "123-45-67"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().openContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}

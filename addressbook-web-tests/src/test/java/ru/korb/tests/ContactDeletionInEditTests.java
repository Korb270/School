package ru.korb.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;

import java.util.List;

public class ContactDeletionInEditTests extends TestBase{

    @Test
    public void testDeletationInEdit(){
        app.getNavigationHelper().openContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov","Korb", "Sber", "Saint-Petersburg", "777-77-77", "8-911-111-22-33", "999-99-99", "korb@yandex.ru", "korb@sber.ru", "3", "March", "1988", "Kudrovo", "123-45-67"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactEdit(before.size() - 1);
        app.getContactHelper().deleteContactInEdit();
        app.getNavigationHelper().openContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        for (int i = 0; i < after.size(); i++){
            Assert.assertEquals(before.get(i), after.get(i));
        }
    }
}

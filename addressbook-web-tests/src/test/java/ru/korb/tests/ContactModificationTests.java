package ru.korb.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().openContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "Korb", "Sber", "Saint-Petersburg", "777-77-77", "8-911-111-22-33", "999-99-99", "korb@yandex.ru", "korb@sber.ru", "3", "March", "1988", "Kudrovo", "123-45-67"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactEdit(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Oleg", "Olegovich", "Olegov", "Korb", "Sber", "Saint-Petersburg", "777-77-77", "8-911-111-22-33", "999-99-99", "korb@yandex.ru", "korb@sber.ru", "3", "March", "1988", "Kudrovo", "123-45-67");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

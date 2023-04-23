package ru.korb.tests;

import org.testng.annotations.Test;
import ru.korb.model.ContactData;

public class ContactModificationInDetailsTests extends TestBase{

    @Test
    public void testContactModificationInDetails(){
        app.getNavigationHelper().openContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Igor", "Vladimirovich", "Rysin", "Korb", "Sber", "Saint-Petersburg", "777-77-77", "8-911-111-22-33", "999-99-99", "korb@yandex.ru", "korb@sber.ru", "3", "March", "1988", "Kudrovo", "123-45-67"));
        }
        app.getContactHelper().initContactDetails();
        app.getContactHelper().initContactEditInDetils();
        app.getContactHelper().fillContactForm(new ContactData("Igor1", "Vladimirovich1", "Rysin1", "Korb", "Sber", "Saint-Petersburg", "777-77-77", "8-911-111-22-33", "999-99-99", "korb@yandex.ru", "korb@sber.ru", "3", "March", "1988", "Kudrovo", "123-45-67"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
    }
}

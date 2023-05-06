package ru.korb.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.goTo().contactPage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Korb").withCompany("Sber").withAddress("Saint-Petersburg").withHome("777-77-77").withMobile("8-911-111-22-33").withWork("999-99-99").withEmail("korb@yandex.ru").withEmail2("korb@sber.ru").withBday("3").withBmonth("March").withByear("1988").withAddress2("Kudrovo").withPhone2("123-45-67");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}

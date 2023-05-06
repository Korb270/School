package ru.korb.tests;

import org.junit.Before;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;
import ru.korb.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationInDetailsTests extends TestBase {

    @Before
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Korb").withCompany("Sber").withAddress("Saint-Petersburg").withHome("777-77-77").withMobile("8-911-111-22-33").withWork("999-99-99").withEmail("korb@yandex.ru").withEmail2("korb@sber.ru").withBday("3").withBmonth("March").withByear("1988").withAddress2("Kudrovo").withPhone2("123-45-67"));
        }
    }

    @Test
    public void testContactModificationInDetails() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Oleg").withMiddlename("Olegovich").withLastname("Olegov").withNickname("Korb").withCompany("Sber").withAddress("Saint-Petersburg").withHome("777-77-77").withMobile("8-911-111-22-33").withWork("999-99-99").withEmail("korb@yandex.ru").withEmail2("korb@sber.ru").withBday("3").withBmonth("March").withByear("1988").withAddress2("Kudrovo").withPhone2("123-45-67");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }
}

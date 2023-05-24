package ru.korb.tests;

import org.junit.Before;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;
import ru.korb.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionInEditTests extends TestBase{

    @Before
    public void ensurePrecondition (){
        if (app.db().contacts().size() == 0){
            app.goTo().contactPage();
            File photo = new File("src/test/resources/Bin.png");
            app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Korb").withPhoto(photo).withCompany("Sber").withAddress("Saint-Petersburg").withHome("777-77-77").withMobile("8-911-111-22-33").withWork("999-99-99").withEmail("korb@yandex.ru").withEmail2("korb@sber.ru").withBday("3").withBmonth("March").withByear("1988").withAddress2("Kudrovo").withPhone2("123-45-67"));
        }
    }

    @Test
    public void testDeletationInEdit(){
        Contacts before = app.db().contacts();
        ContactData deletedContact =  before.iterator().next();
        app.contact().deleteInEdit(deletedContact);
        assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}

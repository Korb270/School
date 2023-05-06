package ru.korb.tests;

import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Before
    public void ensurePrecondition (){
        app.goTo().contactPage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Korb").withCompany("Sber").withAddress("Saint-Petersburg").withHome("777-77-77").withMobile("8-911-111-22-33").withWork("999-99-99").withEmail("korb@yandex.ru").withEmail2("korb@sber.ru").withBday("3").withBmonth("March").withByear("1988").withAddress2("Kudrovo").withPhone2("123-45-67"));
        }
    }

    @Test
    public void testContactDeletion(){
        List<ContactData> before = app.contact().list();
        int index = 0;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        for (int i = 0; i < after.size(); i++){
            Assert.assertEquals(before.get(i), after.get(i));
        }
    }


}

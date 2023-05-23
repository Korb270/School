package ru.korb.tests;

import org.junit.Before;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;
import ru.korb.model.GroupData;

public class ContactAddGroupTests extends TestBase{

    @Before
    public void ensurePrecondition (){
        if (app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Korb").withCompany("Sber").withAddress("Saint-Petersburg").withHome("777-77-77").withMobile("8-911-111-22-33").withWork("999-99-99").withEmail("korb@yandex.ru").withEmail2("korb@sber.ru").withBday("3").withBmonth("March").withByear("1988").withAddress2("Kudrovo").withPhone2("123-45-67"));
        }
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test5").withHeader("test21").withFooter("test32"));
        }
    }

    @Test
    public void testContactAddGroup(){

        ContactData contact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        app.contact().contactAddGroup(contact, group);


        System.out.println("123");
    }


}

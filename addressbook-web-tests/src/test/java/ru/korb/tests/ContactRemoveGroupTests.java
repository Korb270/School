package ru.korb.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;
import ru.korb.model.Contacts;
import ru.korb.model.GroupData;
import ru.korb.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            File photo = new File("src/test/resources/Bin.png");
            app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Korb").withPhoto(photo).withCompany("Sber").withAddress("Saint-Petersburg").withHome("777-77-77").withMobile("8-911-111-22-33").withWork("999-99-99").withEmail("korb@yandex.ru").withEmail2("korb@sber.ru").withBday("3").withBmonth("March").withByear("1988").withAddress2("Kudrovo").withPhone2("123-45-67"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test5").withHeader("test21").withFooter("test32"));
        }
        if (!app.contact().isGroupPresentContact(app.db().contacts())){
            app.contact().contactAddGroup(app.db().contacts().iterator().next(), app.db().groups().iterator().next());
        }
    }

    @Test
    public void testContactAddGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData contWithGroup = new ContactData();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() > 0) {
                contWithGroup = contact;
                break;
            }
        }
        GroupData groupWithCont = app.contact().getFillGroup(groups, contWithGroup);
        app.contact().contactRemoveGroup(contWithGroup, groupWithCont);
        ContactData finalContWithGroup = contWithGroup;
        ContactData after = app.db().contacts().stream().filter((x) -> x.getId() == finalContWithGroup.getId()).findFirst().orElse(null);
        assertThat(after.getGroups(), equalTo(contWithGroup.getGroups().withOut(groupWithCont)));
    }
}

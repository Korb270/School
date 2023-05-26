package ru.korb.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;
import ru.korb.model.GroupData;
import ru.korb.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroupTests extends TestBase {

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
    }

    @Test
    public void testContactAddGroup() {
        Groups groups = app.db().groups();
        ContactData contWithOutGroup = app.contact().getContactData(app.db().contacts(), groups);
        if (contWithOutGroup.getId() == Integer.MAX_VALUE) {
            File photo = new File("src/test/resources/Bin.png");
            app.contact().create(contWithOutGroup.withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Korb").withPhoto(photo).withCompany("Sber").withAddress("Saint-Petersburg").withHome("777-77-77").withMobile("8-911-111-22-33").withWork("999-99-99").withEmail("korb@yandex.ru").withEmail2("korb@sber.ru").withBday("3").withBmonth("March").withByear("1988").withAddress2("Kudrovo").withPhone2("123-45-67"));
            contWithOutGroup = app.contact().getContactData(app.db().contacts(), groups);
        }
        GroupData groupNoCont = app.contact().getAbsentGroup(groups, contWithOutGroup);
        app.contact().contactAddGroup(contWithOutGroup, groupNoCont);
        ContactData finalContWithOutGroup = contWithOutGroup;
        ContactData after = app.db().contacts().stream().filter((x) -> x.getId() == finalContWithOutGroup.getId()).findFirst().orElse(null);
        assertThat(after.getGroups(), equalTo(contWithOutGroup.getGroups().withAdded(groupNoCont)));
    }
}

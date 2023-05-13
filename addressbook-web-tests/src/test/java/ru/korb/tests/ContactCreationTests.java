package ru.korb.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.korb.model.ContactData;
import ru.korb.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withFirstname(split[0]).withMiddlename(split[1]).withLastname(split[2]).withNickname(split[3]).withPhoto(new File(split[4])).withCompany(split[5]).withAddress(split[6]).withHome(split[7]).withMobile(split[8]).withWork(split[9]).withEmail(split[10]).withEmail2(split[11]).withEmail3(split[12]).withBday(split[13]).withBmonth(split[14]).withByear(split[15]).withAddress2(split[16]).withPhone2(split[17])});
            line = reader.readLine();
        }
    return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }
}

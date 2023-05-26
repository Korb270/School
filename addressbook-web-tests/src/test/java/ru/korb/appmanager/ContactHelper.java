package ru.korb.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.korb.model.ContactData;
import ru.korb.model.Contacts;
import ru.korb.model.GroupData;
import ru.korb.model.Groups;

import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        select(By.name("bday"), contactData.getBday());
        select(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }


    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void selectGroupById(int id) {
        wd.findElement(By.xpath("//select[@name='to_group']/option[@value='" + id + "']")).click();
    }

    private void selectFilterGroupById(int id) {
        wd.findElement(By.xpath("//select[@name='group']/option[@value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactEditById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "'")).click();
    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    private void returnContactPage() {
        click(By.linkText("home"));
    }

    public void deleteContactInEdit() {
        click(By.xpath("//form[2]/input[2]"));
    }

    public void initContactDetails(int index) {
        wd.findElements(By.xpath("//img[@alt='Details']")).get(index).click();
    }

    public void initContactEditInDetils() {
        click(By.name("modifiy"));
    }

    public void approveAlert() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
        returnContactPage();
    }

    public void contactPage() {
        if (isElementPresent(By.name("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void modify(ContactData contact) {
        initContactEditById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        contactCache = null;
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        approveAlert();
        contactCache = null;
        contactPage();
    }

    public void contactAddGroup(ContactData contact, GroupData group) {
        contactPage();
        selectContactById(contact.getId());
        selectGroupById(group.getId());
        click(By.name("add"));
        homeWithGroup(group);
    }

    public ContactData getContactData(Contacts contacts, Groups groups) {
        ContactData contWithOutGroup = new ContactData();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size()) {
                contWithOutGroup = contact;
                break;
            }
        }
        return contWithOutGroup;
    }


    public void contactRemoveGroup(ContactData contact, GroupData group) {
        contactPage();
        selectFilterGroupById(group.getId());
        selectContactById(contact.getId());
        click(By.name("remove"));
        homeWithGroup(group);
    }



    public GroupData getAbsentGroup(Groups groups, ContactData contWithOutGroup) {
        boolean isPresentGroup;
        GroupData groupNoCont = new GroupData();
        for (GroupData group : groups) {
            isPresentGroup = false;
            for (GroupData groupInCont : contWithOutGroup.getGroups()) {
                if (groupInCont.getId() == group.getId())
                    isPresentGroup = true;
            }
            if (!isPresentGroup) {
                groupNoCont = group;
                break;
            }
        }
        return groupNoCont;
    }

    public GroupData getFillGroup(Groups groups, ContactData contWithGroup) {
        boolean isPresentGroup;
        GroupData groupWithCont = new GroupData();
        for (GroupData group : groups) {
            isPresentGroup = false;
            for (GroupData groupInCont : contWithGroup.getGroups()) {
                if (groupInCont.getId() == group.getId())
                    isPresentGroup = true;
            }
            if (isPresentGroup) {
                groupWithCont = group;
                break;
            }
        }
        return groupWithCont;
    }

    public boolean isGroupPresentContact(Contacts contacts) {
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() != 0) {
                return true;
            }
        }
        return false;
    }


    public void homeWithGroup(GroupData group) {
        wd.findElement(By.xpath("//a[@href='./?group=" + group.getId() + "']")).click();
    }

    public void deleteInEdit(ContactData contact) {
        initContactEditById(contact.getId());
        deleteContactInEdit();
        contactCache = null;
        contactPage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactEditById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHome(home).withMobile(mobile).withWork(work).withPhone2(phone2).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }


}
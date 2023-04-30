package ru.korb.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.korb.model.ContactData;

import java.util.ArrayList;
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
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        select(By.name("bday"), contactData.getBday());
        select(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactEdit(int index) {
        wd.findElements(By.cssSelector("img[title=\"Edit\"]")).get(index).click();
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

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("td.center"));
        for (WebElement element: elements){
            String name = element.getAttribute("alt");
            ContactData group = new ContactData(element.getAttribute("alt"), null, null,null,null,null,null,null,null,null,null,null,null,null,null,null);
            contacts.add(group);
        }
        return contacts;
    }
}
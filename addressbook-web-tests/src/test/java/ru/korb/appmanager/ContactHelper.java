package ru.korb.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.korb.model.ContactData;
import ru.korb.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
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
        returnContactPage();
    }

    public void contactPage() {
        if (isElementPresent(By.name("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }
    public void modify(ContactData contact) {
        initContactEditById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        approveAlert();
        contactPage();
    }

    public void deleteInEdit(ContactData contact) {
        initContactEditById(contact.getId());
        deleteContactInEdit();
        contactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements){
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withAddress(address));
        }
        return contacts;
    }



}
package manager;

import com.github.javafaker.Faker;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class HelperContact extends HelperBase{

    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        pause(500);
        click(By.xpath("//a[normalize-space()='ADD']"));
    }

    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    public void saveContact() {
        click(By.xpath("//b[text()='Save']"));
    }

    public void returnToHomePage() {
        click(By.xpath("//a[normalize-space()='HOME']"));
    }

    public void openContacts() {
        click(By.xpath("//a[normalize-space()='CONTACTS']"));
    }

    public boolean isContactAddedByName(String name) {
        return isElementPresent(By.xpath("//h2[normalize-space()="+name+"]"));
    }

    public boolean isContactAddedByNameList(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement element:list){
            if (element.getText().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        return isElementPresent(By.xpath("//h3[normalize-space()="+phone+"]"));
    }

    public boolean isContactAddedByPhoneList(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement element:list){
            if (element.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public void chooseContactByName(String name){
        click(By.xpath("//h2[normalize-space()=\""+name+"\"]"));
    }

    public String getContactDetailsText(String name) {
        chooseContactByName(name);
        return wd.findElement(By.xpath("//div[@class='contact-item-detailed_card__50dTS']")).getText();
    }

    public boolean isDescriptionBlank(String name){
        chooseContactByName(name);
        click(By.xpath("//button[normalize-space()='Edit']"));
        String description = wd.findElement(By.xpath("//input[@placeholder='desc']")).getAttribute("value");
        return description.isBlank();
    }

    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public int countAllCounts(){
        openContacts();
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        return list.size();
    }

    public void removeFirstContact() {
        click(By.cssSelector(".contact-item_card__2SOIM:first-child"));
        pause(500);
        click(By.xpath("//button[normalize-space()='Remove']"));
        pause(500);
        refresh();
        }

    public void removeAllContacts() {
         List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        for (WebElement element : list) {
            WebElement element1 = wd.findElement(By.cssSelector(".contact-item_card__2SOIM"));
            element1.click();
            click(By.xpath("//button[text()='Remove']"));
            pause(500);
            openContacts();
        }
    }

    public void addContact(){
        Faker faker = new Faker();
        Contact contact = Contact.builder()
                .Name(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phone(String.valueOf(faker.number().randomNumber(11, true)))
                .email((faker.name().firstName()).toLowerCase()+"."+(faker.name().lastName()).toLowerCase()+"@gmail.com")
                .address(faker.address().streetAddress())
                .description(faker.company().name())
                .build();
        openContactForm();
        fillContactForm(contact);
        saveContact();
        pause(200);
    }

    public void addContactsByNumber(int numberOfContacts){
        for (int i = 0; i < numberOfContacts; i++) {
            addContact();
            pause(200);
        }
    }

    public String getFirstContactName(){
        openContacts();
        return wd.findElement(By.xpath("(//h2)[1]")).getText();
    }
}

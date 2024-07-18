package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperContact extends HelperBase{

    public HelperContact(WebDriver wd) {
        super(wd);
    }


    public void openContactForm() {
        click(By.xpath("//a[normalize-space()='ADD']"));
    }

    public void fillFullContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    public void fillNotFullContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
    }

    public void submit() {
        click(By.xpath("//b[text()='Save']"));
    }

    public void returnToHomePage() {
        click(By.xpath("//a[normalize-space()='HOME']"));
    }

    public void openContacts() {
        click(By.xpath("//a[normalize-space()='CONTACTS']"));
    }

    public boolean isContactAdded(String name) {
        return isElementPresent(By.xpath("//h2[normalize-space()="+name+"]"));
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

}

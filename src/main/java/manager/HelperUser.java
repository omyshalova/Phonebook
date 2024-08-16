package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    //General

    public void openLoginRegistrationForm(){
        click(By.xpath("//a[@href='/login']"));
    }

    public void fillLoginRegistrationForm(String email, String password){
        type(By.xpath("//input[@placeholder='Email']"), email);
        type(By.xpath("//input[@placeholder='Password']"), password);
    }

    public void fillLoginRegistrationForm(User user){
        type(By.xpath("//input[@placeholder='Email']"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword());
    }


    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    //Specific

    public void submitLogin(){
        click(By.xpath("//button[@name='login']"));
    }

    public void submitRegistration(){
        click(By.xpath("//button[@name='registration']"));
    }

    //Checks

    public boolean isRegistered() {
        return isElementPresent(By.xpath("//a[text()='CONTACTS']"));
    }

    public String getMessage(){
        pause(1000);
        return wd.findElement(By.xpath("//h1[text()=' No Contacts here!']")).getText();
    }


    public boolean isNoContactMessagePresent(){
        WebDriverWait wait = new WebDriverWait(wd,5);
        boolean res = wait.until(ExpectedConditions.textToBePresentInElement(
                wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")),
                "No Contacts here!"));
        return res;
    };

    public void login(User user){
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}

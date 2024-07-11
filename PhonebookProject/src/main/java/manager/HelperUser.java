package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

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

    public void submitLogin(){
        click(By.xpath("//button[@name='login']"));
    }

    public void submitRegistration(){
        click(By.xpath("//button[@name='registration']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public boolean isRegistered() {
        return isElementPresent(By.xpath("//a[text()='CONTACTS']"));
    }

    public void loggout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public String getMessage(){
        pause(1000);
        return wd.findElement(By.xpath("//h1[text()=' No Contacts here!']")).getText();
    }
}

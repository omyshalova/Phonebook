package manager;

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

    public void submitLogin(){
        click(By.xpath("//button[@name='login']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void loggout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }
}

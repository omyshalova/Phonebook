package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("testolga@gmail.com").withPassword("Test1101!"));
        }
        app.getHelperContact().openContacts();
        if (app.getHelperContact().countAllCounts() == 0){
            app.getHelperContact().addContactsByNumber(3);
        }
    }

    @Test
    public void removeFirstContact(){
        int numberOfContacts = app.getHelperContact().countAllCounts();
        app.getHelperContact().removeFirstContact();

        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContacts-1);
    }

    @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();

        app.getHelperContact().pause(100);

        Assert.assertEquals(app.getHelperContact().countAllCounts(), 0);
        Assert.assertTrue(app.getHelperContact().isElementPresent(By.xpath("//h1[text()=' No Contacts here!']")));
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperContact().returnToHomePage();
        app.getHelperContact().openContacts();
    }
}
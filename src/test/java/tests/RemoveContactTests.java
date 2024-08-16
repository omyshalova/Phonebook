package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("testolga@gmail.com").withPassword("Test1101!"));
            logger.info("Precondition method was done: logged in the system");
        }
//        app.getHelperContact().openContacts();
//        if (app.getHelperContact().countAllCounts() == 0){
//            app.getHelperContact().addContactsByNumber(3);
//
//        }

        app.getHelperContact().providerContact();
        logger.info("Precondition: logged in the system");
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition(){
        app.getHelperContact().returnToHomePage();
        app.getHelperContact().openContacts();
    }


    @Test(groups = {"smoke"})
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

    //CW

    @Test
    public void removeFirstContactCW(){
        Assert.assertEquals(app.getHelperContact().removeOneContact(),1);
    }

    @Test
    public void removeAllContactsCW(){
        app.getHelperContact().removeAllContactsCW();
        Assert.assertTrue(app.getHelperUser().isNoContactMessagePresent());
    }



}

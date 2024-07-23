package tests;

import manager.ApplicationManager;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().loggout();
            logger.info("Before method logout finish");
        }
    }

    //Positive tests
    @Test
    public void loginSuccess(){
        logger.info("Start test with name 'LoginSuccess'");
        logger.info("Test data ---> email: 'testolga@gmail.com' & password: 'Test1101!'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("testolga@gmail.com", "Test1101!");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Signout' present");
    }

    @Test
    public void loginSuccessModel(){
        logger.info("Test data ---> email: 'testolga@gmail.com' & password: 'Test1101!'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("testolga@gmail.com", "Test1101!");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Signout' present");
    }

    //Negative tests
    @Test
    public void loginWrongEmail(){
        logger.info("Test data ---> email: 'testolgagmail.com' & password: 'Test1101!'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("testolgagmail.com", "Test1101!");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is allert present with error text: 'Wrong email or password'");
    }

    @Test
    public void voidloginWrongPassword(){
        logger.info("Test data ---> email: 'testolga@gmail.com' & password: 'Test'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("testolga@gmail.com", "Test");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is allert present with error text: 'Wrong email or password'");

    }

    @Test
    public void  loginUnregisteredUser(){
        logger.info("Test data ---> email: 'tutu@@gmail.com' & password: 'Tutu1101!'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tutu@gmail.com", "Tutu1101!");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is allert present with error text: 'Wrong email or password'");


    }





}

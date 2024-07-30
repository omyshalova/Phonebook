package tests;

import manager.ApplicationManager;
import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.*;

public class LoginTests extends TestBase{

    @BeforeClass
    public void logClassActions(){
        logger.info("Actions: open, fill, submit login form");
    }

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Precondition method was done: logged out of the system");
        }
    }

    //Positive Tests

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){
//        User user = new User()
//                .withEmail("testolga@gmail.com")
//                .withPassword("Test1101!");

        logData(email, password);

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

        logAssertDetails("SignOut button is present");

    }

    @Test(dataProvider = "loginModels", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user){

        logData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

        logAssertDetails("SignOut button is present");
    }

    // Negative Tests

    @Test
    public void loginWrongEmail(){
        User user = new User()
                .withEmail("testolgagmail.com")
                .withPassword("Test1101!");

        logData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        String alert = "Wrong email or password";

        Assert.assertTrue(app.getHelperUser().isAlertPresent(alert));

        logAssertDetails(alert + " alert is present");
    }

    @Test
    public void loginWrongPassword(){
        User user = new User()
                .withEmail("testolga@gmail.com")
                .withPassword("Test");

        logData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        String alert = "Wrong email or password";

        Assert.assertTrue(app.getHelperUser().isAlertPresent(alert));

        logAssertDetails(alert + " alert is present");

    }

    @Test
    public void  loginUnregisteredUser(){
        User user = new User()
                .withEmail("tutu@gmail.com")
                .withPassword("Tutu1101!");

        logData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        String alert = "Wrong email or password";

        Assert.assertTrue(app.getHelperUser().isAlertPresent(alert));

        logAssertDetails(alert + " alert is present");

    }
}

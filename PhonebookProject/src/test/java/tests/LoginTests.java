package tests;

import manager.ApplicationManager;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

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
        logger.info("Precondition: not logged in the system");
    }

    //Positive Tests

    @Test
    public void loginSuccess(){
        User user = new User()
                .withEmail("testolga@gmail.com")
                .withPassword("Test1101!");

        logger.info("Test data: email - {} & password - {}", user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Assert: SignOut button is present");
    }

    @Test
    public void loginSuccessModel(){
        User user = new User()
                .withEmail("testolga@gmail.com")
                .withPassword("Test1101!");

        logger.info("Test data: email - {} & password - {}", user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Assert: SignOut button is present");
    }

    // Negative Tests

    @Test
    public void loginWrongEmail(){
        User user = new User()
                .withEmail("testolgagmail.com")
                .withPassword("Test1101!");

        logger.info("Test data: email - {} & password - {}", user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert: Alert 'Wrong email or password' is present");
    }

    @Test
    public void loginWrongPassword(){
        User user = new User()
                .withEmail("testolga@gmail.com")
                .withPassword("Test");

        logger.info("Test data: email - {} & password - {}", user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert: Alert 'Wrong email or password' is present");

    }

    @Test
    public void  loginUnregisteredUser(){
        User user = new User()
                .withEmail("tutu@gmail.com")
                .withPassword("Tutu1101!");

        logger.info("Test data: email - {} & password - {}", user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert: Alert 'Wrong email or password' is present");
    }
}

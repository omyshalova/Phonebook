package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{


    @BeforeClass(alwaysRun = true)
    public void logClassActions(){
        logger.info("Actions: open, fill, submit registration form");
    }

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Precondition method was done: logged out of the system");
        }
    }

    //Positive tests
    @Test(dataProvider = "registrationPositive", dataProviderClass = DataProviderUser.class)
    public void registrationSuccess(User user) {
        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        String message = "No Contacts here!";

        Assert.assertTrue(app.getHelperUser().isRegistered());
        Assert.assertEquals(app.getHelperUser().getMessage() ,message);
        Assert.assertTrue(app.getHelperUser().isNoContactMessagePresent());

        logAssertDetails("1. Contact button is present");
        logAssertDetails("2. " + message + " is present");
    }

    @Test
    public void registrationSuccessModel() {

        int i = (int) (System.currentTimeMillis() / 1000) % 36000;
        User user = new User()
                .withEmail("smith" + i + "@gmail.com")
                .withPassword("Smith1235813!");

        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());

        logAssertDetails("Contact button is present");
            }

    @Test(description = "Bug report #1234")//, enabled = false)
    public void registrationWrongEmail(){
        User user = new User()
                .withEmail("smithgmail.com")
                .withPassword("Smith1235813!");

        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        String alert = "Wrong email or password";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(alert));

        logAssertDetails(alert + " is present");
    }

    @Test()
    public void registrationWrongPassword1(){
        User user = new User()
                .withEmail("smith@gmail.com")
                .withPassword("Smith123");

        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        String alert = "Wrong email or password";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(alert));

        logAssertDetails(alert + " is present");
    }

    @Test
    public void registrationExistUser(){
        User user = new User()
                .withEmail("testolga@gmail.com")
                .withPassword("Test1101!");

        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        String alert = "User already exist";

        Assert.assertTrue(app.getHelperUser().isAlertPresent(alert));

        logAssertDetails(alert + " is present");
    }
}

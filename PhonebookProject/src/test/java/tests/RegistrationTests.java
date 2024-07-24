package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeClass
    public void logClassActions(){
        logger.info("Actions: open, fill, submit registration form");
    }

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Precondition method was done: logged out of the system");
        }
        logger.info("Precondition: not logged in the system");
    }

    //Positive tests
    @Test
    public void registrationSuccess() {
        //new Random().nextInt(1000)+1000;
        int i = (int) (System.currentTimeMillis() / 1000) % 36000;
        User user = new User()
                .withEmail("smith" + i + "@gmail.com")
                .withPassword("Smith1235813!");

        logger.info("Test data: email - {} & password - {}", user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());
        Assert.assertEquals(app.getHelperUser().getMessage() ,"No Contacts here!");
        Assert.assertTrue(app.getHelperUser().isNoContactMessagePresent());

        logger.info("Assert1: Contact button is present");
        logger.info("Assert2: Message 'No Contacts here!' is present");

    }

    @Test
    public void registrationSuccessModel() {

        int i = (int) (System.currentTimeMillis() / 1000) % 36000;
        User user = new User()
                .withEmail("smith" + i + "@gmail.com")
                .withPassword("Smith1235813!");

        logger.info("Test data: email - {} & password - {}", user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());

        logger.info("Assert: Contact button is present");

    }

    @Test(description = "Bug report #1234")//, enabled = false)
    public void registrationWrongEmail(){
        User user = new User()
                .withEmail("smithgmail.com")
                .withPassword("Smith1235813!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationWrongPassword(){
        User user = new User()
                .withEmail("smith@gmail.com")
                .withPassword("Smith123");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationExistUser(){
        User user = new User()
                .withEmail("testolga@gmail.com")
                .withPassword("Test1101!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }


}

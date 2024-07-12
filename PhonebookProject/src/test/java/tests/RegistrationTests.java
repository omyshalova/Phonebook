package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().loggout();
        }
    }

    //Positive tests
    @Test
    public void registrationSuccess() {
        //new Random().nextInt(1000)+1000;
        int i = (int) (System.currentTimeMillis() / 1000) % 36000;
        User user = new User()
                .withEmail("smith" + i + "@gmail.com")
                .withPassword("Smith1235813!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());
        Assert.assertEquals(app.getHelperUser().getMessage() ,"No Contacts here!");
        Assert.assertTrue(app.getHelperUser().isNoContactMessagePresent());

    }

    @Test
    public void registrationSuccessModel() {

        int i = (int) (System.currentTimeMillis() / 1000) % 36000;
        User user = new User()
                .withEmail("smith" + i + "@gmail.com")
                .withPassword("Smith1235813!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());

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

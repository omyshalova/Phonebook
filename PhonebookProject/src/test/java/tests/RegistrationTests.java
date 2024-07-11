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

        int i = (int) (System.currentTimeMillis() / 1000) % 36000;
        User user = new User()
                .setEmail("smith" + i + "@gmail.com")
                .setPassword("Smith1235813!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());
        Assert.assertEquals(app.getHelperUser().getMessage() ,"No Contacts here!");

    }

    @Test
    public void registrationSuccessModel() {

        int i = (int) (System.currentTimeMillis() / 1000) % 36000;
        User user = new User()
                .setEmail("smith" + i + "@gmail.com")
                .setPassword("Smith1235813!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());

    }

}

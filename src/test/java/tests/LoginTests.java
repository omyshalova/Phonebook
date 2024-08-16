package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeClass(alwaysRun = true)
    public void logClassActions(){
        logger.info("Actions: open, fill, submit login form");
    }

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Precondition method was done: logged out of the system");
        }
    }

    //Positive Tests

    @Test(dataProvider = "loginPositive", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){
        logUserData(email, password);

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logAssertDetails("SignOut button is present");

    }

    @Test(dataProvider = "loginPositiveModels", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user){
        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logAssertDetails("SignOut button is present");
    }

    // Negative Tests

    @Test(dataProvider = "loginNegativeEmail", dataProviderClass = DataProviderUser.class,
            groups = {"smoke"})
    public void loginWrongEmail(User user){
        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        String alert = "Wrong email or password";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(alert));
        logAssertDetails(alert + " alert is present");
    }

    @Test(dataProvider = "loginNegativePassword", dataProviderClass = DataProviderUser.class)
    public void loginWrongPassword(User user){
        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        String alert = "Wrong email or password";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(alert));
        logAssertDetails(alert + " alert is present");
    }

    @Test(dataProvider = "loginNegativeUnregisteredUser", dataProviderClass = DataProviderUser.class)
    public void  loginUnregisteredUser(User user){
        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        String alert = "Wrong email or password";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(alert));
        logAssertDetails(alert + " alert is present");
    }

    @Test(dataProvider = "loginFile", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelDP(User user){
        logUserData(user.getEmail(), user.getPassword());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logAssertDetails("SignOut button is present");
    }
}

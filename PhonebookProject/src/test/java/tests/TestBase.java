package tests;

import manager.ApplicationManager;
import models.Contact;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite(alwaysRun = true)
    public void setApp(){
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        app.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void startLogger(Method m){
        logger.info("   Start:{} ({})", m.getName(), m.toGenericString());
    }

    @AfterMethod(alwaysRun = true)
    public void endLogger(Method m){
        logger.info("************************************************************************************");
    }

    public void logUserData(String email, String password) {
        logger.info("Test data: email - {} & password - {}", email, password);
    }

    public void logContactData(Contact contact) {
        logger.info("Test data: " +
                        "name - {}, " +
                        "last name - {}, " +
                        "phone - {}, " +
                        "email - {}, " +
                        "address - {}, " +
                        "description - {}." ,
                contact.getName(),
                contact.getLastName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getAddress(),
                contact.getDescription());
    }

    public void logAssertDetails(String text){
        logger.info("Assert: {}", text);
    }




}

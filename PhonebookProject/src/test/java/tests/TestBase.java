package tests;

import manager.ApplicationManager;
import models.Contact;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    static ApplicationManager app = new ApplicationManager();
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setApp(){
        app.init();
    }

    @BeforeMethod
    public void startLogger(Method m){
        logger.info("   Start:{} ({})", m.getName(), m.toGenericString());
    }

    @AfterMethod
    public void endLogger(Method m){
        logger.info("************************************************************************************");
    }

    @AfterSuite
    public void tearDown(){
        app.stop();
    }

    public void logData(User user) {
        logger.info("Test data: email - {} & password - {}", user.getEmail(), user.getPassword());
    }

    public void logAssertDetails(String text){
        logger.info("Assert: {}", text);
    }

    public void logContact(Contact contact) {
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


}

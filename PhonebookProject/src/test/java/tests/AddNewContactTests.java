package tests;

import com.github.javafaker.Faker;
import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void preCondition(){
        logger.info("   Preconditions:");
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("testolga@gmail.com").withPassword("Test1101!"));
            logger.info("Precondition method was done: logged in the system");
        }
        logger.info("Precondition: logged in the system");
    }

    //POSITIVE

    @Test(dataProvider = "addNewContactPositiveAll", dataProviderClass = DataProviderContact.class)
    public void addNewContactSuccessAll(Contact contact){

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        logContactData(contact);

        softAssert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByNameList(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByPhoneList(contact.getPhone()));
        softAssert.assertTrue(app.getHelperContact().getContactDetailsText(contact.getName()).contains(contact.getEmail()));
        softAssert.assertFalse(app.getHelperContact().isDescriptionBlank(contact.getName()));

        logAssertDetails("1. contact addition by name " + contact.getName());
        logAssertDetails("2. contact addition by phone " + contact.getPhone());
        logAssertDetails("3. contact addition by name (list) " + contact.getName());
        logAssertDetails("4. contact addition by phone (list) " + contact.getPhone());
        logAssertDetails("5. contact addition by contact details " + contact.getName());
        logAssertDetails("6. not blank description field by name " + contact.getName());
    }

    @Test(dataProvider = "addNewContactPositiveRequired", dataProviderClass = DataProviderContact.class)
    public void addNewContactSuccessRequiredFields(Contact contact){

        logContactData(contact);

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        softAssert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByNameList(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByPhoneList(contact.getPhone()));
        softAssert.assertTrue(app.getHelperContact().getContactDetailsText(contact.getName()).contains(contact.getEmail()));
        softAssert.assertTrue(app.getHelperContact().isDescriptionBlank(contact.getName()));

        logAssertDetails("1. contact addition by name " + contact.getName());
        logAssertDetails("2. contact addition by phone " + contact.getPhone());
        logAssertDetails("3. contact addition by name (list) " + contact.getName());
        logAssertDetails("4. contact addition by phone (list) " + contact.getPhone());
        logAssertDetails("5. contact addition by contact details " + contact.getName());
        logAssertDetails("6. blank description field by name " + contact.getName());
    }

    //NEGATIVE

    @Test(dataProvider = "addContactNegativeName", dataProviderClass = DataProviderContact.class)
    public void addNewContactNegative_Name(Contact contact){
        int i = new Random().nextInt(1000);
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();

        logContactData(contact);

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-1" + i + ".png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);

        logAssertDetails("1. Add page is displayed");
        logAssertDetails("2. Number of contacts hasn't changed");

    }

    @Test(dataProvider = "addContactNegativeLastName", dataProviderClass = DataProviderContact.class)
    public void addNewContactNegative_LastName(Contact contact){
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        logContactData(contact);

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);

        logAssertDetails("1. Add page is displayed");
        logAssertDetails("2. Number of contacts hasn't changed");
    }

    @Test(dataProvider = "addContactNegativeAddress", dataProviderClass = DataProviderContact.class)
    public void addNewContactNegative_Address(Contact contact){
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        logContactData(contact);

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);

        logAssertDetails("1. Add page is displayed");
        logAssertDetails("2. Number of contacts hasn't changed");
    }

    @Test(dataProvider = "addContactNegativePhone", dataProviderClass = DataProviderContact.class)
    public void addNewContactNegative_Phone(Contact contact){
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        logContactData(contact);

        String alert = "Phone not valid:";

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(alert));
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);

        logAssertDetails("1. Add page is displayed");
        logAssertDetails("2. " + alert + "is present");
        logAssertDetails("3. Number of contacts hasn't changed");

    }

    @Test(enabled = false)
    public void addNewContactEmptyEmail(){
        // Contact with empty email field can be added
        // in Swagger UI - email field is not stated as required
        // https://contactapp-telran-backend.herokuapp.com/swagger-ui/index.html#/Contact%20Controller/addCar
        // though as per T26 from Requirements - "Contact email - Customer email is required"
        // https://docs.google.com/document/d/1DAfXRNxwmu0SxZJlEc1ohRj6gI4I1kCzfTe4c2Xx4wM/edit

        int numberOfContactsBefore = app.getHelperContact().countAllCounts();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = String.valueOf(faker.number().randomNumber(11, true));
        String email = "";
        String address = faker.address().streetAddress();
        String description = faker.company().name();

        Contact contact = Contact.builder()
                .Name(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .address(address)
                .description(description)
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);
    }

    @Test(dataProvider = "addContactNegativeEmail", dataProviderClass = DataProviderContact.class)
    public void addNewContactNegative_Email(Contact contact){
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        logContactData(contact);

        String alert = "Email not valid:";

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(alert));
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);

        logAssertDetails("1. Add page is displayed");
        logAssertDetails("2. " + alert + "is present");
        logAssertDetails("3. Number of contacts hasn't changed");
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperContact().returnToHomePage();
        app.getHelperContact().openContacts();
    }

}

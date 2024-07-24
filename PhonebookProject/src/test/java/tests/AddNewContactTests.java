package tests;

import com.github.javafaker.Faker;
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
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("testolga@gmail.com").withPassword("Test1101!"));
        }
    }

    //POSITIVE

    @Test
    public void addNewContactSuccessAll(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = String.valueOf(faker.number().randomNumber(11, true));
        String email = firstName.toLowerCase()+"."+lastName.toLowerCase()+"@gmail.com";
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

        softAssert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByNameList(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByPhoneList(contact.getPhone()));
        softAssert.assertTrue(app.getHelperContact().getContactDetailsText(contact.getName()).contains(contact.getEmail()));
        softAssert.assertFalse(app.getHelperContact().isDescriptionBlank(contact.getName()));
    }

    @Test
    public void addNewContactSuccessRequiredFields(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = String.valueOf(faker.number().randomNumber(11, true));
        String email = firstName.toLowerCase()+"."+lastName.toLowerCase()+"@gmail.com";
        String address = faker.address().streetAddress();

        Contact contact = Contact.builder()
                .Name(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .address(address)
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        softAssert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByNameList(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().isContactAddedByPhoneList(contact.getPhone()));
        softAssert.assertTrue(app.getHelperContact().getContactDetailsText(contact.getName()).contains(contact.getEmail()));
        softAssert.assertTrue(app.getHelperContact().isDescriptionBlank(contact.getName()));
    }

    //NEGATIVE

    @Test
    public void addNewContactEmptyName(){
        int i = new Random().nextInt(1000);
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();
        Faker faker = new Faker();
        String firstName = "";
        String lastName = faker.name().lastName();
        String phone = String.valueOf(faker.number().randomNumber(11, true));
        String email = lastName.toLowerCase()+"@gmail.com";
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
        app.getHelperContact().getScreen("src/test/screenshots/screen-1" + i + ".png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);

    }

    @Test
    public void addNewContactEmptyLastName(){
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = "";
        String phone = String.valueOf(faker.number().randomNumber(11, true));
        String email = firstName.toLowerCase()+"@gmail.com";
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

    @Test
    public void addNewContactEmptyAddress(){
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = String.valueOf(faker.number().randomNumber(11, true));
        String email = firstName.toLowerCase()+"."+lastName.toLowerCase()+"@gmail.com";
        String address = "";
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

    @Test
    public void addNewContactEmptyPhone(){
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = "";
        String email = firstName.toLowerCase()+"."+lastName.toLowerCase()+"@gmail.com";
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
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid:"));
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);
    }

    @Test
    public void addNewContactWrongPhoneFormat(){
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = String.valueOf(faker.number().randomNumber(7, true));
        String email = firstName.toLowerCase()+"."+lastName.toLowerCase()+"@gmail.com";
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
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid:"));
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);
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

    @Test
    public void addNewContactWrongEmailFormat(){
        int numberOfContactsBefore = app.getHelperContact().countAllCounts();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = String.valueOf(faker.number().randomNumber(11, true));
        String email = firstName.toLowerCase()+"."+lastName.toLowerCase()+"gmail.com";
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
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid:"));
        Assert.assertEquals(app.getHelperContact().countAllCounts(), numberOfContactsBefore);
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperContact().returnToHomePage();
        app.getHelperContact().openContacts();
    }

}

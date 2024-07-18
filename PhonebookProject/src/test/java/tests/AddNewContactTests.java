package tests;

import com.github.javafaker.Faker;
import models.Contact;
import models.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddNewContactTests extends TestBase{

    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("testolga@gmail.com").withPassword("Test1101!"));
        }
    }

    @Test
    public void addNewContactSuccess(){
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
        app.getHelperContact().fillFullContactForm(contact);
        app.getHelperContact().submit();

        app.getHelperUser().pause(500);

        softAssert.assertTrue(app.getHelperContact().isContactAdded(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().getContactDetailsText(contact.getName()).contains(contact.getEmail()));
        softAssert.assertFalse(app.getHelperContact().isDescriptionBlank(contact.getName()));
    }

    @Test
    public void addNewContactSuccess1(){
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
        app.getHelperContact().fillNotFullContactForm(contact);
        app.getHelperContact().submit();

        softAssert.assertTrue(app.getHelperContact().isContactAdded(contact.getName()));
        softAssert.assertTrue(app.getHelperContact().getContactDetailsText(contact.getName()).contains(contact.getEmail()));
        softAssert.assertTrue(app.getHelperContact().isDescriptionBlank(contact.getName()));
    }


    @AfterMethod
    public void postCondition(){
        app.getHelperContact().returnToHomePage();
        app.getHelperContact().openContacts();
    }

}

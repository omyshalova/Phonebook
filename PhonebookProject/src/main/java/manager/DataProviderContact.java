package manager;

import com.github.javafaker.Faker;
import models.Contact;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = String.valueOf(faker.number().randomNumber(11, true));
        String email = firstName.toLowerCase()+"."+lastName.toLowerCase()+"@gmail.com";
        String address = faker.address().streetAddress();
        String description = faker.company().name();

        Faker faker1 = new Faker();
        String firstName1 = faker.name().firstName();
        String lastName1 = faker.name().lastName();
        String phone1 = String.valueOf(faker.number().randomNumber(11, true));
        String email1 = firstName1.toLowerCase()+"."+lastName1.toLowerCase()+"@gmail.com";
        String address1 = faker.address().streetAddress();
        String description1 = faker.company().name();

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .Name(firstName1)
                .lastName(lastName1)
                .phone(phone1)
                .email(email1)
                .address(address1)
                .description(description1)
                .build()});

        list.add(new Object[]{Contact.builder()
                .Name(firstName1)
                .lastName(lastName1)
                .phone(phone1)
                .email(email1)
                .address(address1)
                .description(description1)
                .build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactEmptyPhone(){
        List<Object[]> list = new ArrayList<>();

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = "wwwwwwwwww";
        String email = firstName.toLowerCase()+"."+lastName.toLowerCase()+"@gmail.com";
        String address = faker.address().streetAddress();
        String description = faker.company().name();

        Faker faker1 = new Faker();
        String firstName1 = faker.name().firstName();
        String lastName1 = faker.name().lastName();
        String phone1 = "";
        String email1 = firstName1.toLowerCase()+"."+lastName1.toLowerCase()+"@gmail.com";
        String address1 = faker.address().streetAddress();
        String description1 = faker.company().name();

        Faker faker2 = new Faker();
        String firstName2 = faker.name().firstName();
        String lastName2 = faker.name().lastName();
        String phone2 = String.valueOf(faker.number().randomNumber(6, true));;
        String email2 = firstName2.toLowerCase()+"."+lastName2.toLowerCase()+"@gmail.com";
        String address2 = faker.address().streetAddress();
        String description2 = faker.company().name();

        list.add(new Object[]{Contact.builder()
                .Name(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .address(address)
                .description(description)
                .build()});

        list.add(new Object[]{Contact.builder()
                .Name(firstName1)
                .lastName(lastName1)
                .phone(phone1)
                .email(email1)
                .address(address1)
                .description(description1)
                .build()});

        list.add(new Object[]{Contact.builder()
                .Name(firstName2)
                .lastName(lastName2)
                .phone(phone2)
                .email(email2)
                .address(address2)
                .description(description2)
                .build()});

        return list.iterator();
    }
}

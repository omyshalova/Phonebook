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
        list.add(new Object[]{});//object to be added
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactPositiveAll(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> addNewContactPositiveRequired(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .build()});

        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .build()});

        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addContactNegativePhone(){
        List<Object[]> list = new ArrayList<>();
        //short phone number
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(5,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        //letter instead of numbers
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone("www")
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        //empty phone field
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone("")
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        //long phone number
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(17,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addContactNegativeName(){
        List<Object[]> list = new ArrayList<>();
        //empty name
        list.add(new Object[]{Contact.builder()
                .Name("")
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .Name("")
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .Name("")
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addContactNegativeLastName(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName("")
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName("")
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName("")
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addContactNegativeAddress(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address("")
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address("")
                .description(new Faker().company().name())
                .build()});
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address("")
                .description(new Faker().company().name())
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addContactNegativeEmail(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .Name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        return list.iterator();
    }

}

package manager;

import com.github.javafaker.Faker;
import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
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
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
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
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .build()});

        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .build()});

        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
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
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(5,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        //letter instead of numbers
        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone("www")
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        //empty phone field
        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone("")
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});

        //long phone number
        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
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
                .name("")
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .name("")
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .name("")
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
                .name(new Faker().name().firstName())
                .lastName("")
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
                .lastName("")
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
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
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address("")
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "@gmail.com")
                .address("")
                .description(new Faker().company().name())
                .build()});
        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
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
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        list.add(new Object[]{Contact.builder()
                .name(new Faker().name().firstName())
                .lastName(new Faker().name().lastName())
                .phone(String.valueOf(new Faker().number().randomNumber(11,true)))
                .email(new Faker().name().firstName().toLowerCase() + new Faker().name().lastName() + "gmail.com")
                .address(new Faker().address().streetAddress())
                .description(new Faker().company().name())
                .build()});//object to be added
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[] all = line.split(";");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }

}

package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginPositive(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"testolga@gmail.com","Test1101!"});
        list.add(new Object[]{"testolga@gmail.com","Test1101!"});
        list.add(new Object[]{"testolga@gmail.com","Test1101!"});
        list.add(new Object[]{"testolga@gmail.com","Test1101!"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginPositiveModels(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("sonya@gmail.com").withPassword("Ss12345$")});
        list.add(new Object[]{new User().withEmail("mara@gmail.com").withPassword("Mmar123456$")});
        list.add(new Object[]{new User().withEmail("testolga@gmail.com").withPassword("Test1101!")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginNegativeEmail(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("testolgagmail.com").withPassword("Test1101!")});
        list.add(new Object[]{new User().withEmail("testolga@gmailcom").withPassword("Test1101!")});
        list.add(new Object[]{new User().withEmail("testolga@gmail").withPassword("Test1101!")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginNegativePassword(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("testolga@gmail.com").withPassword("Test110")});
        list.add(new Object[]{new User().withEmail("testolga@gmail.com").withPassword("")});
        list.add(new Object[]{new User().withEmail("testolga@gmail.com").withPassword("qqq")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginNegativeUnregisteredUser(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("tutu@tutu.com").withPassword("Test110")});
        list.add(new Object[]{new User().withEmail("blabla@blala.com").withPassword("4569")});
        list.add(new Object[]{new User().withEmail("nogo@nogo.com").withPassword("qqq")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> registrationPositive(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User()
                .withEmail("smith"+(new Random().nextInt(1000)+1000)+"@gmail.com")
                .withPassword("Smith1235813!")});
        list.add(new Object[]{new User()
                .withEmail("smith"+(new Random().nextInt(1000)+1000)+"@gmail.com")
                .withPassword("Smith1235813!")});
        list.add(new Object[]{new User()
                .withEmail("smith"+(new Random().nextInt(1000)+1000)+"@gmail.com")
                .withPassword("Smith1235813!")});
        return list.iterator();
    }

}

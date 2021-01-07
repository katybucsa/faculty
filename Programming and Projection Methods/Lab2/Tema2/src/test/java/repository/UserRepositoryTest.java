package repository;

import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class UserRepositoryTest {
    UserRepository userrepo;
    @Before
    public void setUp() throws Exception {
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("D:\\A2S2\\MPP\\Lab\\Lab2\\Tema2\\db.config"));
            //System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }
        userrepo=new UserRepository(serverProps,new UserValidator());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findOne() {
        User user=userrepo.findOne("user1");
        assertEquals(user.getName(),"User1");
    }
}
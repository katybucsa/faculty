package repository;

import domain.Ride;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Properties;
import static org.junit.Assert.*;

public class RideRepositoryTest {
    RideRepository repo;
    RideRepository repo1;
    Ride ride;

    @Before
    public void setUp() throws Exception {
        /*ride = new Ride(3,"Madrid", "25-06-2019", "14:00");
        ApplicationContext springJavaConfig=new AnnotationConfigApplicationContext(FirmaDeTransportConfig.class);
        repo=springJavaConfig.getBean(RideRepository.class);
        ApplicationContext factory = new
                ClassPathXmlApplicationContext("D:\\A2S2\\MPP\\Lab\\Lab2\\Tema2\\src\\main\\resources\\spring_config.xml");
        //repo= factory.getBean(RideRepository.class);*/
    }

    @After
    public void tearDown() throws Exception {
        repo = null;
        ride=null;
    }

    @Test
    public void findOne() {
        Ride r=repo.findOne(1);
        assertEquals(r.getDestination(),"Cluj");
    }

    @Test
    public void save() {
        repo.save(ride);
        assert (repo.size() == 3);
    }

    @Test
    public void update() {
        Ride r=new Ride(1,"Cluj",ride.getDate(),ride.getHour());
        repo.update(r);
    }

    @Test
    public void size() {
        assert (repo.size() == 3);
        repo.delete(3);
        assert (repo.size() == 2);
        repo.save(ride);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void delete() {
        int s=repo.size();
        repo.delete(4);
        assert (repo.size() == s);
        repo.delete(3);
        //assert (repo.size() == s-1);

    }
}
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {
    Student s;
    @Before
    public void setUp() throws Exception {
        s=new Student(1,"Alin",221,"alin@yahoo.com","Camelia");
    }

    @After
    public void tearDown() throws Exception {
        s=null;
    }

    @Test
    public void getIdStudent() {
        assert(s.getID()==1);
    }

    @Test
    public void getNume() {
        assert (s.getNume()=="Alin");
    }

    @Test
    public void getGrupa() {
        assert(s.getGrupa()==221);
    }

    @Test
    public void getEmail() {
        assert(s.getEmail()=="alin@yahoo.com");
    }

    @Test
    public void getIndrumator() {
        assert(s.getIndrumator()=="Camelia");
    }

    @Test
    public void setID(){
        s.setID(2);
        assert(s.getID()==2);
    }

    @Test
    public void setNume() {
        s.setNume("Andrei");
        assert (s.getNume()=="Andrei");
    }

    @Test
    public void setGrupa() {
        s.setGrupa(222);
        assert(s.getGrupa()==222);
    }

    @Test
    public void setEmail() {
        s.setEmail("andrei@yahoo.com");
        assert(s.getEmail()=="andrei@yahoo.com");
    }

    @Test
    public void setIndrumator() {
        s.setIndrumator("Alina");
        assert(s.getIndrumator()=="Alina");
    }
}
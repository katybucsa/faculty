package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemaTest {
    Tema  t;
    @Before
    public void setUp() throws Exception {
        t=new Tema(1,"numere complexe",2,1);
    }

    @After
    public void tearDown() throws Exception {
        t=null;
    }

    @Test
    public void getID() {
        assert(t.getID()==1);
    }

    @Test
    public void getDescriere() {
        assert(t.getDescriere()=="numere complexe");
    }

    @Test
    public void getDeadline() {
        assert(t.getDeadline()==2);
    }

    @Test
    public void getSaptPrimireTema() {
        assert(t.getSaptPrimireTema()==1);
    }

    @Test
    public void setID() {
        t.setID(2);
        assert(t.getID()==2);
    }

    @Test
    public void setDescriere() {
        t.setDescriere("tasks");
        assert(t.getDescriere()=="tasks");
    }

    @Test
    public void setDeadline() {
        t.setDeadline(3);
        assert(t.getDeadline()==3);
    }

    @Test
    public void setSaptPrimireTema() {
        t.setSaptPrimireTema(2);
        assert(t.getSaptPrimireTema()==2);
    }
}
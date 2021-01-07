package domain;

import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class NotaTest {
    Nota n;

    @Before
    public void setUp() throws Exception {
        n = new Nota(1, 2, 10, LocalDateTime.parse("2018-11-13T17:58:38.393331300"));
    }

    @After
    public void tearDown() throws Exception {
        n = null;
    }

    @Test
    public void getID() {
        assert (n.getID().getKey() == 1);
        assert (n.getID().getValue() == 2);
    }

    @Test
    public void getNota() {
        assert(n.getNota()==10);
    }

    @Test
    public void getData() {
        assert(n.getData().toString().equals("2018-11-13T17:58:38.393331300"));
    }

    @Test
    public void setID() {
        n.setID(new Pair(1,3));
        assert(n.getID().getKey()==1);
        assert(n.getID().getValue()==3);
    }

    @Test
    public void setNota() {
        n.setNota(9.5);
        assert(n.getNota()==9.5);
    }

    @Test
    public void setData() {
        n.setData(LocalDateTime.parse("2018-09-21T17:58:38.393331300"));
        assert(n.getData().toString().equals("2018-09-21T17:58:38.393331300"));
    }
}
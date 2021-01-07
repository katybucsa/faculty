package factory;

import domain.Nota;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class NotaRepositoryTest {
    NotaRepository repo;
    Nota n1, n2,n3;

    @Before
    public void setUp() throws Exception {
        repo = new NotaRepository(new NotaValidator());
        n1 = new Nota(1, 2, 10, LocalDateTime.parse("2018-11-13T17:58:38.393331300"));
        n2=new Nota(1, 2, 10, LocalDateTime.parse("2018-11-13T17:58:38.393331300"));
    }

    @After
    public void tearDown() throws Exception {
        repo = null;
        n1 = n2 = null;
    }

    @Test
    public void save() {
        try{
            repo.save(n3);
        }catch (IllegalArgumentException iae){assert(true);}
        repo.save(n1);
        assert(repo.size()==1);
        try{
            repo.save(n2);
        }catch(RepoException re){assert(true);}
    }
}
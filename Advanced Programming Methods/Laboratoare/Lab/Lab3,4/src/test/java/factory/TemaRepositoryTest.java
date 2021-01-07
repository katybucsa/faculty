package factory;

import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TemaRepositoryTest {
    TemaRepository repo;
    Tema t1,t2,t3,t4,t5;
    @Before
    public void setUp() throws Exception {
        repo=new TemaRepository(new TemaValidator());
        t1=new Tema(1,"numere complexe",2,1);
        t2=new Tema(1,"tasks",3,2);
        t3=null;
        t4=new Tema(-1,"fsd",-2,-3);
        t5=new Tema(5,"catalog",9,3);
    }

    @After
    public void tearDown() throws Exception {
        repo=null;
        t1=t2=t3=t4=t5=null;
    }

    @Test
    public void findOne() {
        repo.save(t1);
        Tema t=repo.findOne(1);
        assert(t.getDescriere()=="numere complexe");
        try{
            repo.findOne(null);
        }catch(IllegalArgumentException iae){ assert(true);}

    }

    @Test
    public void save() {
        assert (repo.size() == 0);
        repo.save(t1);
        assert (repo.size() == 1);
        try {
            repo.save(t3);
        } catch (IllegalArgumentException iae) {
            assert (true);
        }
        try {
            repo.save(t4);
        } catch (ValidationException ve) {
            assert (true);
        }
        repo.save(t2);
        assert(repo.size()==1);
        repo.save(t5);
        assert(repo.size()==2);
    }

    @Test
    public void update() {
        try {
            repo.update(null);
        } catch (IllegalArgumentException iae) {
            assert (true);
        }
        try {
            repo.update(t4);
        } catch (ValidationException ve) {
            assert (true);
        }
        assert (repo.update(t1) == t1);
        assert (repo.update(t2) == null);
        assert (repo.size() == 1);
    }

    @Test
    public void size() {
        assert (repo.size() == 0);
        repo.save(t1);
        assert (repo.size() == 1);
        repo.update(t2);
        repo.save(t5);
        assert (repo.size() == 2);
    }

    @Test
    public void findAll() {
        repo.save(t1);
        repo.save(t5);
        Iterable<Tema> all=repo.findAll();
        Iterator<Tema> it= all.iterator();
        for(Tema s:all)
            assert(s==t1 || s==t5);
    }

    @Test
    public void delete() {
        try{
            repo.delete(null);
        }catch (IllegalArgumentException iae){ assert(true);}

        repo.save(t1);
        assert(repo.delete(2)==null);
        assert(repo.delete(1)==t1);
    }
}
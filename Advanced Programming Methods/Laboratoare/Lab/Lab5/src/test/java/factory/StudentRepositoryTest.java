package factory;

import domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Optional;


public class StudentRepositoryTest {
    StudentRepository stRepo;
    Student s1, s2, s3, s4, s5;

    @Before
    public void setUp() throws Exception {
        stRepo = new StudentRepository(new StudentValidator());
        s1 = new Student(1, "Alin", 221, "alin@yahoo.com", "Camelia");
        s2 = new Student(1, "Bogdan", 223, "bogdan@gmail.com", "Alina");
        s3 = null;
        s4 = new Student(-2, "dsf", 2332, "fsdf", "sffd");
        s5 = new Student(32, "Bogdan", 223, "bogdan@gmail.com", "Alina");

    }

    @After
    public void tearDown() throws Exception {
        stRepo = null;
        s1 = s2 = s3 = s4 = s5 = null;
    }

    @Test
    public void findOne() {
        stRepo.save(s1);
        Optional<Student> s = stRepo.findOne(1);
        assert (s.get().getNume() == "Alin");
        try {
            stRepo.findOne(null);
        } catch (IllegalArgumentException iae) {
            assert (iae.getMessage().equals("Id invalid!\n"));
        }
    }

    @Test
    public void save() {
        assert (stRepo.size() == 0);
        stRepo.save(s1);
        assert (stRepo.size() == 1);
        try {
            stRepo.save(s3);
        } catch (IllegalArgumentException iae) {
            assert (iae.getMessage().equals("Entitate invalida!\n"));
        }
        try {
            stRepo.save(s4);
        } catch (ValidationException ve) {
            assert (ve.getMessage().equals("Id invalid!\nGrupa invalida!\n"));
        }
        stRepo.save(s2);
        assert (stRepo.size() == 1);
        stRepo.save(s5);
        assert (stRepo.size() == 2);
    }

    @Test
    public void update() {
        try {
            stRepo.update(null);
        } catch (IllegalArgumentException iae) {
            assert (true);
        }
        try {
            stRepo.update(s4);
        } catch (ValidationException ve) {
            assert (true);
        }
        assert (stRepo.update(s1).get() == s1);
        assert (!stRepo.update(s2).isPresent());
        assert (stRepo.size() == 1);
    }

    @Test
    public void size() {
        assert (stRepo.size() == 0);
        stRepo.save(s1);
        assert (stRepo.size() == 1);
        stRepo.update(s2);
        stRepo.save(s5);
        assert (stRepo.size() == 2);
    }

    @Test
    public void findAll() {
        stRepo.save(s1);
        stRepo.save(s5);
        Iterable<Student> all = stRepo.findAll();
        Iterator<Student> it = all.iterator();
        for (Student s : all)
            assert (s == s1 || s == s5);
    }

    @Test
    public void delete() {
        try {
            stRepo.delete(null);
        } catch (IllegalArgumentException iae) {
            assert (true);
        }

        stRepo.save(s1);
        assert (!stRepo.delete(2).isPresent());
        assert (stRepo.delete(1).get() == s1);
    }
}
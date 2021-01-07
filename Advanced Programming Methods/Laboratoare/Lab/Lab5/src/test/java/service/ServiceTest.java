package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import factory.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

public class ServiceTest {
    Service serv;
    StudentRepository sr;
    TemaRepository tr;
    NotaRepository nr;
    Tema t1, t2;
    Student s1, s2, s3;
    Nota n1,n2,n3;

    @Before
    public void setUp() throws Exception {
        sr = new StudentRepository(new StudentValidator());
        tr = new TemaRepository(new TemaValidator());
        nr = new NotaRepository(new NotaValidator());
        serv = new Service(sr, tr,nr);
        t1 = new Tema(1, "numere complexe", 2, 1);
        t2 = new Tema(5, "catalog", 9, 3);
        s1 = new Student(1, "Alin", 221, "alin@yahoo.com", "Camelia");
        s2 = new Student(1, "Bogdan", 223, "bogdan@gmail.com", "Alina");
        s3 = new Student(4, "Bogdan", 226, "bogdan@gmail.com", "Alina");
    }

    @After
    public void tearDown() throws Exception {
        sr = null;
        tr = null;
        serv = null;
        t1 = t2 = null;
        s1 = s2 = s3 = null;
        File f= new File("D:\\A2S1\\MAP\\Laboratoare\\Lab5\\src\\main\\java\\studentFile/Alin.txt");
        f.delete();
        f= new File("D:\\A2S1\\MAP\\Laboratoare\\Lab5\\src\\main\\java\\studentFile/Bogdan.txt");
        f.delete();
    }

    @Test
    public void findOneStudent() {
        serv.addStudent(1, "Alin", 221, "alin@yahoo.com", "Camelia");
        Optional<Student> s = serv.findOneStudent(1);
        assert (s.get().getNume() == "Alin");
        serv.addStudent(1, "Bogdan", 223, "bogdan@gmail.com", "Alina");
        s = serv.findOneStudent(1);
        assert (s.get().getNume() == "Bogdan");
    }

    @Test
    public void addStudent() {
        serv.addStudent(1, "Alin", 221, "alin@yahoo.com", "Camelia");
        assert (serv.sizeStudent() == 1);
        serv.addStudent(1, "Bogdan", 223, "bogdan@gmail.com", "Alina");
        assert (serv.sizeStudent() == 1);
        serv.addStudent(4, "Bogdan", 226, "bogdan@gmail.com", "Alina");
        assert (serv.sizeStudent() == 2);
    }

    @Test
    public void updateStudent() {
        serv.addStudent(1, "Alin", 221, "alin@yahoo.com", "Camelia");
        assert (serv.findOneStudent(1).get().getNume().equals("Alin"));
        serv.updateStudent(1, "Bogdan", 223, "bogdan@gmail.com", "Alina");
        assert (serv.findOneStudent(1).get().getNume().equals("Bogdan"));
    }

    @Test
    public void sizeStudent() {
        assert (serv.sizeStudent() == 0);
        serv.addStudent(1, "Alin", 221, "alin@yahoo.com", "Camelia");
        assert (serv.sizeStudent() == 1);
        serv.addStudent(1, "Bogdan", 223, "bogdan@gmail.com", "Alina");
        assert (serv.sizeStudent() == 1);
        serv.addStudent(4, "Bogdan", 226, "bogdan@gmail.com", "Alina");
        assert (serv.sizeStudent() == 2);
        serv.deleteStudent(1);
        assert (serv.sizeStudent() == 1);
    }

    @Test
    public void findAllStudents() {
        serv.addStudent(1, "Alin", 221, "alin@yahoo.com", "Camelia");
        serv.addStudent(1, "Bogdan", 223, "bogdan@gmail.com", "Alina");
        serv.addStudent(4, "Bogdan", 226, "bogdan@gmail.com", "Alina");
        Iterable<Student> it = serv.findAllStudents();
    }

    @Test
    public void deleteStudent() {
        serv.addStudent(1, "Alin", 221, "alin@yahoo.com", "Camelia");
        serv.addStudent(1, "Bogdan", 223, "bogdan@gmail.com", "Alina");
        serv.addStudent(4, "Bogdan", 226, "bogdan@gmail.com", "Alina");
        assert (serv.sizeStudent() == 2);
        serv.deleteStudent(12);
        assert (serv.sizeStudent() == 2);
        serv.deleteStudent(1);
        assert (serv.sizeStudent() == 1);

    }

    @Test
    public void findOneTema() {
        serv.addTema(1, "numere complexe", 2, 1);
        assert (serv.findOneTema(1).get().getDescriere().equals("numere complexe"));
        assert (!serv.findOneTema(213).isPresent());
    }

    @Test
    public void addTema() {
        serv.addTema(1, "catalog", 9, 3);
        assert (serv.sizeTema() == 1);
        serv.addTema(1, "numere complexe", 2, 1);
        assert (serv.sizeTema() == 1);
        serv.findOneTema(1).get().getDescriere().equals("numere complexe");
        serv.addTema(5, "catalog", 9, 3);
        assert (serv.sizeTema() == 2);
    }

    @Test
    public void updateTema() {
        serv.addTema(1, "catalog", 9, 3);
        assert (serv.sizeTema() == 1);
        assert (serv.updateTema(5, "catalog", 9, 3).isPresent());
        assert (serv.sizeTema() == 2);
        assert (!serv.updateTema(1, "numere complexe", 2, 1).isPresent());
        assert (serv.sizeTema() == 2);
    }

    @Test
    public void sizeTema() {
        serv.addTema(1, "catalog", 9, 3);
        assert (serv.sizeTema() == 1);
        serv.addTema(1, "catalog studenti", 10, 5);
        assert (serv.sizeTema() == 1);
        serv.updateTema(5, "catalog", 9, 3);
        assert (serv.sizeTema() == 2);
        serv.updateTema(1, "numere complexe", 2, 1);
        assert (serv.sizeTema() == 2);
    }

    @Test
    public void findAllTeme() {
        serv.addTema(1, "numere complexe", 2, 1);
        serv.addTema(5, "catalog", 9, 3);
        Iterable<Tema> t = serv.findAllTeme();
    }

    @Test
    public void deleteTema() {
        serv.addTema(1, "numere complexe", 2, 1);
        serv.addTema(5, "catalog", 9, 3);
        assert (serv.sizeTema() == 2);
        assert (!serv.deleteTema(2).isPresent());
        assert (serv.sizeTema() == 2);
        assert (serv.deleteTema(1).isPresent());
        assert (serv.sizeTema() == 1);
    }

    @Test
    public void prelungesteTermen() {
        serv.addTema(1, "numere complexe", 2, 1);
        serv.addTema(5, "catalog", 9, 3);
        try {
            serv.prelungesteTermen(43);
        } catch (IllegalArgumentException re) {
            assert (true);
        }
        try {
            serv.prelungesteTermen(1);
        } catch (IllegalArgumentException re) {
            assert (true);
        }
        serv.prelungesteTermen(5);
        assert (serv.findOneTema(5).get().getDeadline() == 10);
    }

    @Test
    public void addNota(){
        serv.addStudent(1, "Alin", 221, "alin@yahoo.com", "Camelia");
        serv.addStudent(4, "Bogdan", 226, "bogdan@gmail.com", "Alina");
        serv.addTema(1, "numere complexe", 2, 1);
        serv.addTema(5, "catalog", 7, 3);
        serv.addTema(6, "tasks", 6, 5);
        try{
            serv.addNota(12,3,5, LocalDateTime.now(),"ferferwg");
        }catch(IllegalArgumentException iae){assert(true);}
        try{
            serv.addNota(1,1,8,LocalDateTime.now(),"ferf");
        }catch(IllegalArgumentException e){assert(true);}
        assert(serv.addNota(1,5,10,LocalDateTime.now(),"fsfg")==10);
        assert (serv.addNota(4,6,10,LocalDateTime.now(),"gfdgd")==7.5);

    }
}
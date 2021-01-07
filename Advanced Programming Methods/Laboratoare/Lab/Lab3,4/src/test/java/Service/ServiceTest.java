package Service;

import domain.Student;
import domain.Tema;
import factory.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {
    Service serv;
    StudentRepository sr;
    TemaRepository tr;
    Tema t1,t2;
    Student s1,s2,s3;
    @Before
    public void setUp() throws Exception {
        sr = new StudentRepository(new StudentValidator());
        tr = new TemaRepository(new TemaValidator());
        serv = new Service(sr,tr);
        t1 = new Tema(1,"numere complexe",2,1);
        t2 = new Tema(5,"catalog",9,3);
        s1 = new Student(1,"Alin",221,"alin@yahoo.com","Camelia");
        s2 = new Student(1,"Bogdan",223,"bogdan@gmail.com","Alina");
        s3 = new Student(4,"Bogdan",226,"bogdan@gmail.com","Alina");
    }

    @After
    public void tearDown() throws Exception {
        sr = null;
        tr = null;
        serv = null;
        t1 = t2 = null;
        s1 = s2 = s3 = null;
    }

    @Test
    public void findOneStudent() {
        serv.addStudent(1,"Alin",221,"alin@yahoo.com","Camelia");
        Student s = serv.findOneStudent(1);
        assert(s.getNume()=="Alin");
        serv.addStudent(1,"Bogdan",223,"bogdan@gmail.com","Alina");
        s = serv.findOneStudent(1);
        assert(s.getNume()=="Bogdan");
    }

    @Test
    public void addStudent() {
        serv.addStudent(1,"Alin",221,"alin@yahoo.com","Camelia");
        assert(serv.sizeStudent()==1);
        serv.addStudent(1,"Bogdan",223,"bogdan@gmail.com","Alina");
        assert(serv.sizeStudent()==1);
        serv.addStudent(4,"Bogdan",226,"bogdan@gmail.com","Alina");
        assert(serv.sizeStudent()==2);
    }

    @Test
    public void updateStudent() {
        serv.addStudent(1,"Alin",221,"alin@yahoo.com","Camelia");
        assert(serv.findOneStudent(1).getNume().equals("Alin"));
        serv.updateStudent(1,"Bogdan",223,"bogdan@gmail.com","Alina");
        assert(serv.findOneStudent(1).getNume().equals("Bogdan"));
    }

    @Test
    public void sizeStudent() {
        assert(serv.sizeStudent()==0);
        serv.addStudent(1,"Alin",221,"alin@yahoo.com","Camelia");
        assert(serv.sizeStudent()==1);
        serv.addStudent(1,"Bogdan",223,"bogdan@gmail.com","Alina");
        assert(serv.sizeStudent()==1);
        serv.addStudent(4,"Bogdan",226,"bogdan@gmail.com","Alina");
        assert(serv.sizeStudent()==2);
        serv.deleteStudent(1);
        assert (serv.sizeStudent()==1);
    }

    @Test
    public void findAllStudents() {
        serv.addStudent(1,"Alin",221,"alin@yahoo.com","Camelia");
        serv.addStudent(1,"Bogdan",223,"bogdan@gmail.com","Alina");
        serv.addStudent(4,"Bogdan",226,"bogdan@gmail.com","Alina");
        Iterable<Student> it = serv.findAllStudents();
    }

    @Test
    public void deleteStudent() {
        serv.addStudent(1,"Alin",221,"alin@yahoo.com","Camelia");
        serv.addStudent(1,"Bogdan",223,"bogdan@gmail.com","Alina");
        serv.addStudent(4,"Bogdan",226,"bogdan@gmail.com","Alina");
        assert (serv.sizeStudent()==2);
        serv.deleteStudent(12);
        assert (serv.sizeStudent()==2);
        serv.deleteStudent(1);
        assert (serv.sizeStudent()==1);

    }

    @Test
    public void findOneTema() {
        serv.addTema(1,"numere complexe",2,1);
        assert(serv.findOneTema(1).getDescriere().equals("numere complexe"));
        assert(serv.findOneTema(213)== null);
    }

    @Test
    public void addTema() {
        serv.addTema(1,"catalog",9,3);
        assert(serv.sizeTema()==1);
        serv.addTema(1,"numere complexe",2,1);
        assert (serv.sizeTema()==1);
        assert(serv.findOneTema(1).getDescriere().equals("numere complexe"));
        serv.addTema(5,"catalog",9,3);
        assert (serv.sizeTema()==2);
    }

    @Test
    public void updateTema() {
        serv.addTema(1,"catalog",9,3);
        assert(serv.sizeTema()==1);
        assert(serv.updateTema(5,"catalog",9,3)== null);
        assert(serv.sizeTema()==2);
        assert(serv.updateTema(1,"numere complexe",2,1) != null);
        assert(serv.sizeTema()==2);
    }

    @Test
    public void sizeTema() {
        serv.addTema(1,"catalog",9,3);
        assert(serv.sizeTema()==1);
        serv.addTema(1,"catalog studenti", 10,5);
        assert(serv.sizeTema()==1);
        assert(serv.updateTema(5,"catalog",9,3)== null);
        assert(serv.sizeTema()==2);
        assert(serv.updateTema(1,"numere complexe",2,1) != null);
        assert(serv.sizeTema()==2);
    }

    @Test
    public void findAllTeme() {
        serv.addTema(1,"numere complexe",2,1);
        serv.addTema(5,"catalog",9,3);
        Iterable<Tema> t = serv.findAllTeme();
    }

    @Test
    public void deleteTema() {
        serv.addTema(1,"numere complexe",2,1);
        serv.addTema(5,"catalog",9,3);
        assert(serv.sizeTema()==2);
        assert(serv.deleteTema(2)==null);
        assert(serv.sizeTema()==2);
        assert (serv.deleteTema(1)!=null);
        assert(serv.sizeTema()==1);
    }

    @Test
    public void prelungesteTermen(){
        serv.addTema(1,"numere complexe",2,1);
        serv.addTema(5,"catalog",9,3);
        try{
            serv.prelungesteTermen(43);
        }catch (IllegalArgumentException re){ assert(true);}
        try{
            serv.prelungesteTermen(1);
        }catch(IllegalArgumentException re){ assert(true);}
        serv.prelungesteTermen(5);
        assert(serv.findOneTema(5).getDeadline()== 10);
    }
}
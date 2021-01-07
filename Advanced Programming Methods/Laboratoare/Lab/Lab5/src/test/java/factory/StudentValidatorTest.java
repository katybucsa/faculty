package factory;

import domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentValidatorTest {
    Student s1,s2,s3,s4;
    StudentValidator valid;
    @Before
    public void setUp() throws Exception {
        s1=new Student(-1,"Bogdan",223,"bogdan@gmail.com","Alina");
        s2=new Student(1,"Bogdan",2232,"bogdan@gmail.com","Alina");
        s3=new Student(-1,"Bogdan",2223,"bogdan@gmail.com","Alina");
        s4=new Student(1,"Bogdan",223,"bogdan@gmail.com","Alina");
        valid= new StudentValidator();
    }

    @After
    public void tearDown() throws Exception {
        s1=s2=s3=s4=null;
        valid=null;
    }

    @Test
    public void validate() {
        try{
            valid.validate(s1);
        }catch(ValidationException ve){ assert(ve.getMessage().equals("Id invalid!\n"));}

        try {
            valid.validate(s2);
        }catch(ValidationException ve) {
            assert (ve.getMessage().equals("Grupa invalida!\n"));
        }

        try{
            valid.validate(s3);
        }catch(ValidationException ve){assert(ve.getMessage().equals("Id invalid!\nGrupa invalida!\n"));}

        valid.validate(s4);
    }
}
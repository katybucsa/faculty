package factory;

import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemaValidatorTest {
    Tema t1,t2,t3;
    TemaValidator valid;
    @Before
    public void setUp() throws Exception {
        t1=  new Tema(1,"numere complexe",2,1);
        t2= new Tema(21,"fdsf",2,2);
        t3= new Tema(34,"fsd",15,43);
        valid =new TemaValidator();
    }

    @After
    public void tearDown() throws Exception {
        t1=t2=t3=null;
        valid=null;
    }

    @Test
    public void validate() {
        valid.validate(t1);
        try{
            valid.validate(t2);
        }catch(ValidationException ve){ assert(ve.getMessage().equals("Numar tema invalid!\n"));}

        try{
            valid.validate(t3);
        }catch(ValidationException ve){assert(ve.getMessage().equals("Numar tema invalid!\nTermen limita de predare invalid!\nSaptamana deprimire tema invalida!\n"));}
    }
}
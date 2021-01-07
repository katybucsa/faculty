package factory;

import domain.Tema;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * TemaRepository - repository that stores objects of Tema type
 * extends AbstractRepository - ID=Integer, E=Tema
 */
public class TemaRepository extends AbstractRepository<Integer,Tema> {
    public TemaRepository(Validator<Tema> v){
        super(v);
    }
}

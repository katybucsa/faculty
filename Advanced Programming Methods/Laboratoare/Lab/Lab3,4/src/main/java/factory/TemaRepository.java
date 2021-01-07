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

    /**
     * extends the deadline for an existing homework, increasing by 1 the homework deadline
     * if the homework does not exist  or the current week exceeded the homework deadline,
     *                  an IllegalArgumentException is thrown
     * @param id - the id of the entity for which the deadline will be changed
     */
}

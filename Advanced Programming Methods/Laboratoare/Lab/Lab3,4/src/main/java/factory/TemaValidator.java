package factory;

import domain.Tema;

/**
 * TemaValidator implements Validator interface - E=Tema
 */
public class TemaValidator implements Validator<Tema> {

    /**
     * validates a student
     * @param t - Tema object to be validated
     * @throws ValidationException if the id, deadline or week of receiving of the Tema object are not valid
     */
    public void validate(Tema t){
        String err="";
        if(t.getID()<0 || t.getID()>14)
            err+="Numar tema invalid!\n";
        if(t.getDeadline()<1 || t.getDeadline()>14)
            err+="Termen limita de predare invalid!\n";
        if(t.getSaptPrimireTema()<1 || t.getSaptPrimireTema()>14)
            err+="Saptamana deprimire tema invalida!\n";
        if(err!="")
            throw new ValidationException(err);
    }
}

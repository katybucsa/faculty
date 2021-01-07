package sample.repository;

import sample.domain.Nota;

public class NotaValidator implements Validator<Nota> {
    public void validate(Nota nota){
        String err="";
        if(nota.getID().getKey()<1)
            err += "Id student invalid!\n";
        if(nota.getID().getValue()<1 || nota.getID().getValue()>14)
            err += "Numar tema invalid!\n";
        if(nota.getNota()<1 || nota.getNota()>10)
            err += "Nota invalida!\n";
        if(err!="")
            throw new ValidationException(err);
    }
}

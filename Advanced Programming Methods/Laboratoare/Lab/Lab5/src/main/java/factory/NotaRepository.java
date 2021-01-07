package factory;

import domain.Nota;
import javafx.util.Pair;

import java.util.Optional;

public class NotaRepository extends AbstractRepository<Pair<Integer,Integer>, Nota> {
    public NotaRepository(Validator<Nota> v){
        super(v);
    }

    public Nota save(Nota n){
        if(n==null)
            throw new IllegalArgumentException("Nota invalida!\n");
        valid.validate(n);
        Optional<Nota> nt = super.findOne(n.getID());
        if(nt.isPresent())
            throw new RepoException("Nota existenta!\n");
        map.put(n.getID(), n);
        return null;
    }
}

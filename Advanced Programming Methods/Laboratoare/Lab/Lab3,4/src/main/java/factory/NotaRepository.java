package factory;

import domain.Nota;
import javafx.util.Pair;

public class NotaRepository extends AbstractRepository<Pair<Integer,Integer>, Nota> {
    public NotaRepository(Validator<Nota> v){
        super(v);
    }

    public Nota save(Nota n){
        if(n==null)
            throw new IllegalArgumentException("Nota invalida!\n");
        valid.validate(n);
        Nota nt = super.findOne(n.getID());
        if(nt != null)
            throw new RepoException("Nota existenta!\n");
        map.put(n.getID(), n);
        return null;
    }
}

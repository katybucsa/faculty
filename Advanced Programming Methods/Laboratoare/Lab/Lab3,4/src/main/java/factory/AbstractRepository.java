package factory;

import java.util.HashMap;

/**
 * class AbstractRepository implements RepositoryInterface and stores entities that extends HasID interface
 */
public abstract class AbstractRepository<ID,E extends HasID<ID>> implements RepositoryInterface<ID,E> {
    protected Validator<E> valid;
    protected HashMap<ID,E> map;

    /**
     * constructor
     * @param v - the validator for the entities of type E
     */
    public AbstractRepository(Validator<E> v){
        valid=v;
        map=new HashMap<>();
    }

    public E findOne(ID id){
        if(id==null)
            throw new IllegalArgumentException("Id invalid!\n");
        return map.get(id);
    }

    public E save(E e) {
        if(e==null)
            throw new IllegalArgumentException("Entitate invalida!\n");
        valid.validate(e);
        E elem = findOne(e.getID());
        map.put(e.getID(), e);
        return elem;
    }

    public E update(E e){
        if(e==null)
            throw new IllegalArgumentException("Entitate invalida!\n");
        valid.validate(e);
        E el=map.get(e.getID());
        if(el!=null){
            map.put(e.getID(),e);
            return null;
        }
        map.put(e.getID(),e);
        return e;
    }

    public int size(){
        return map.size();
    }

    public Iterable<E> findAll(){
        return map.values();
    }

    public E delete(ID id){
        if(id==null)
            throw new IllegalArgumentException("Id invalid!\n");
        E el=map.get(id);
        if(el==null)
            return null;
        map.remove(id);
        return el;
    }
}
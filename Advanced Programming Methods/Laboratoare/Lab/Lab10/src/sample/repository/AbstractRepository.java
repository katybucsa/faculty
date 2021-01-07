package sample.repository;

import java.util.HashMap;
import java.util.Optional;

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

    public Optional<E> findOne(ID id){
        if(id==null)
            throw new IllegalArgumentException("Id invalid!\n");
        return Optional.ofNullable(map.get(id));
    }

    public E save(E e) {
        if(e==null)
            throw new IllegalArgumentException("Entitate invalida!\n");
        valid.validate(e);
        return  map.put(e.getID(),e);
    }

    public Optional<E> update(E e){
        if(e==null)
            throw new IllegalArgumentException("Entitate invalida!\n");
        valid.validate(e);
       if(map.containsKey(e.getID())){
           map.put(e.getID(),e);
           return Optional.empty();
       }
       map.put(e.getID(),e);
       return Optional.of(e);
    }

    public int size(){
        return map.size();
    }

    public Iterable<E> findAll(){
        return map.values();
    }

    public Optional<E> delete(ID id){
        if(id==null)
            throw new IllegalArgumentException("Id invalid!\n");
        return Optional.ofNullable(map.remove(id));
    }
}
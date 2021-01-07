package sample.repository;


import java.util.Collection;
import java.util.HashMap;

/**
 * class AbstractRepository implements RepositoryInterface and stores entities that extends HasID interface
 */
public abstract class AbstractRepository<ID,E extends HasID<ID>> implements RepositoryInterface<ID,E> {
    protected HashMap<ID,E> map;

    /**
     * constructor
     */
    public AbstractRepository(){
        map=new HashMap<>();
    }

    public E save(E e) {
        if(e==null)
            throw new IllegalArgumentException("Entitate invalida!\n");
        return  map.put(e.getID(),e);
    }

    public E findOne(ID id){
        if(id==null)
            throw new IllegalArgumentException("Id invalid!\n");
        return map.get(id);
    }

    public Collection<E> findAll(){
        return map.values();
    }

}
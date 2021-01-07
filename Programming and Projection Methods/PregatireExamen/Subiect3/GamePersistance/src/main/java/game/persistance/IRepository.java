package game.persistance;

public interface IRepository<ID,E> {

    E findOne(ID id);


    void save(E e) throws ValidationException;


    void update(E e);


    int size();

    Iterable<E> findAll();


    void delete(ID id);
}

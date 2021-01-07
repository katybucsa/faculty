package factory;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> - type if entities saved in repository
 */
public interface RepositoryInterface<ID,E> {
    /**
     *
     * @param id - the id of the entity to be returned; id must not be null
     * @return the entity with the specified id, or null if there if no entity with the given id
     * @throws IllegalArgumentException if id id null
     */
    Optional<E> findOne(ID id);

    /**
     *
     * @param e -the entity to be saved; e must be not null
     * @return null if the given entity is saved, otherwise returns the entity(id already exists)
     * @throws ValidationException if the entity is not valid
     * @throws IllegalArgumentException if the given entity is null
     */
    E save(E e) throws ValidationException;

    /**
     *
     * @param e - entity must not be null
     * @return null if the entity is updated, otherwise returns the entity(the id doesn't exist)
     * @throws IllegalArgumentException if the given entity is null
     * @throws ValidationException if the entity id not valid
     */
    Optional<E> update(E e);

    /**
     *
     * @return the number of entities saved in repository
     */
    int size();

    /**
     *
     * @return all the entities saved in repository
     */
    Iterable<E> findAll();

    /**
     * removes the entity with the specified id
     * @param id - must not be null
     * @return the removed entity or null if there is no entity with the given id
     * @throws IllegalArgumentException if the given id is null
     */
    Optional<E> delete(ID id);
}
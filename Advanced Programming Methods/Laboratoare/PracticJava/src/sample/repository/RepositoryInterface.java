package sample.repository;

import java.util.Collection;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> - type if entities saved in repository
 */
public interface RepositoryInterface<ID,E> {

    Collection<E> findAll();
}
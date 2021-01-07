package sample.repository;

/**
 * validation operations Validator interface
 * @param <E> - the entity that will be validated
 */
public interface Validator<E> {
    /**
     * validates an entity
     * @param e - the entity to be validated
     * @throws ValidationException if the entity is not valid
     */
    void validate(E e) throws ValidationException;
}

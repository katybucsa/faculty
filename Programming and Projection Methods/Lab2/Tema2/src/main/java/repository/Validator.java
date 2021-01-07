package repository;

public interface Validator<E> {
    public void validate(E entity) throws ValidationException;
}

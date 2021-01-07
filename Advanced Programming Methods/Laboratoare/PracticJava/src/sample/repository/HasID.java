package sample.repository;

/**
 * getter and setter methods HasId interface
 * @param <ID> - the entities that implement HasID interface must have an attribute of type ID
 */
public interface HasID<ID> {
    /**
     * getter method
     * @return the ID of the entity
     */
    ID getID();

    /**
     * setter method
     * changes the ID of the entity
     * @param id - the new ID of the entity
     */
    void setID(ID id);
}

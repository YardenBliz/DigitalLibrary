package library.repository;

import java.io.IOException;
import java.util.List;

public interface IDao<ID extends java.io.Serializable,T> {
    void delete(ID id);
    boolean add(T entity);
    T getByID(ID id) throws IllegalArgumentException, IOException;
    List<T> getAll();
    boolean save(T entity) throws IllegalArgumentException;
}


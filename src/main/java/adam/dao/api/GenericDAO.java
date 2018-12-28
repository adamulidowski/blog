package adam.dao.api;

import java.util.List;


public interface GenericDAO<T, ID> {

    void save(T object);


    void update(T object);


    void delete(T object);


    List<T> getAll();


    T getById(ID id);

    List<String> getAllToString();
}

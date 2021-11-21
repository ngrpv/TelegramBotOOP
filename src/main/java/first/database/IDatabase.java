package first.database;

import first.user.User;

import javax.persistence.Entity;
import java.util.ArrayList;

public interface IDatabase {
    void saveOrUpdate(Object entity);

    void persist(Object entity);

    <T> T get(Class<T> aClass, java.io.Serializable serializable);

    <T> ArrayList<T> getAll(Class<T> tClass);

    <T> ArrayList<T> getTop(Class<T> aClass, int count, String orderedBy);
}

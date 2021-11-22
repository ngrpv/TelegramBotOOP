package first.repository;

import java.util.ArrayList;

public interface IRepository<T> {
    void save(T entity);
    void saveOrUpdate(T entity);
    T get(long id);
    ArrayList<T> getAll();
    ArrayList<T> getTop(int count, String orderBy);
}

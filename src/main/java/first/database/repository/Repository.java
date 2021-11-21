package first.database.repository;

import first.database.IDatabase;

import java.util.ArrayList;

public class Repository<T> implements IRepository<T> {
    final Class<T> typeParameterClass;
    IDatabase database;

    public Repository(Class<T> typeParameterClass, IDatabase database) {
        this.typeParameterClass = typeParameterClass;
        this.database = database;
    }


    @Override
    public void save(T entity) {
        database.persist(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        database.saveOrUpdate(entity);
    }

    @Override
    public T get(long id) {
       return database.get(typeParameterClass, id);
    }

    @Override
    public ArrayList<T> getAll() {
        return database.getAll(typeParameterClass);
    }

    @Override
    public ArrayList<T> getTop(int count, String orderBy) {
        return database.getTop(typeParameterClass, count, orderBy);
    }


}

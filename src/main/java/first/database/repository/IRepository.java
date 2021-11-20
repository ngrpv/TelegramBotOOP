package first.database.repository;

import first.games.IGame;

import java.util.ArrayList;

public interface IRepository<T> {
    void Save(T entity);
    void Delete(T entity);
    void Update(T entity);
    void UpdateOrAdd(T entity);
    T Get(long id);
    ArrayList<T> GetAll();


    void UpdateOrAdd(IGame gameState);

    void Save(IGame gameState);
}

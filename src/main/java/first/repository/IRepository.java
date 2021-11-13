package first.repository;

public interface IRepository<T> {
    void Save(T entity);
    void Delete(T id);
    void Update(T entity);
    void UpdateOrAdd(T entity);
    T Get(long id);
}

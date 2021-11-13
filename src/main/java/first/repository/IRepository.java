package first.repository;

public interface IRepository<T> {
    void Save(T entity);
    void Delete(long id);
    void Update(T entity);
    void UpdateOrAdd(T entity);
}

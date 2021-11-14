package first.repository;

import first.IGame;
import first.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class Repository<T> implements IRepository<T> {
    final Class<T> typeParameterClass;
    SessionFactory sessionFactory;

    public Repository(Class<T> typeParameterClass, SessionFactory sessionFactory) {
        this.typeParameterClass = typeParameterClass;
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void Save(T entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
    }

    @Override
    public void Delete(T entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
    }

    @Override
    public void Update(T entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
    }

    @Override
    public void UpdateOrAdd(T entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }
    }

    @Override
    public void UpdateOrAdd(IGame entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }
    }

    @Override
    public void Save(IGame gameState) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(gameState);
            transaction.commit();
        }
    }

    @Override
    public T Get(long id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var entity = session.get(typeParameterClass, id);
            transaction.commit();
            return entity;
        }
    }

    @Override
    public ArrayList<T> GetAll() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var criteriaBuilder = session.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(typeParameterClass);
            query.from(User.class);
            var users = session.createQuery(query).getResultList();
            transaction.commit();
            return (ArrayList<T>) users;
        }
    }
}

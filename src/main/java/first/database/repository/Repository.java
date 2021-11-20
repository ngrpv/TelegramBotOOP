package first.database.repository;

import first.games.IGame;
import first.user.User;
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
    public void save(T entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
    }

    @Override
    public void delete(T entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
    }

    @Override
    public void update(T entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
    }

    @Override
    public void updateOrAdd(T entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }
    }

    @Override
    public T get(long id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var entity = session.get(typeParameterClass, id);
            transaction.commit();
            return entity;
        }
    }

    @Override
    public ArrayList<T> getAll() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var criteriaBuilder = session.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(typeParameterClass);
            query.from(typeParameterClass);
            var users = session.createQuery(query).getResultList();
            transaction.commit();
            return (ArrayList<T>) users;
        }
    }

    @Override
    public ArrayList<T> getTop(int count, String orderBy) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var criteriaBuilder = session.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(typeParameterClass);
            var root = query.from(typeParameterClass);
            query.select(root);
            query.orderBy(criteriaBuilder.desc(root.get(orderBy)));
            var entities = session.createQuery(query).setFirstResult(0).setMaxResults(count).getResultList();
            transaction.commit();
            return (ArrayList<T>) entities;
        }
    }


}

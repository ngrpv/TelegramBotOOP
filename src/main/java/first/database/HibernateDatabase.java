package first.database;

import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;

public class HibernateDatabase implements IDatabase {
    SessionFactory sessionFactory;

    public HibernateDatabase() {
    sessionFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public void saveOrUpdate(Object entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }
    }

    @Override
    public void persist(Object entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
    }

    @Override
    public <T> T get(Class<T> aClass, Serializable serializable) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var entity = session.get(aClass, serializable);
            transaction.commit();
            return entity;
        }
    }

    @Override
    public <T> ArrayList<T> getAll(Class<T> tClass) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var criteriaBuilder = session.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(tClass);
            query.from(tClass);
            var users = session.createQuery(query).getResultList();
            transaction.commit();
            return (ArrayList<T>) users;
        }
    }

    @Override
    public <T> ArrayList<T> getTop(Class<T> aClass, int count, String orderedBy) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var criteriaBuilder = session.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(aClass);
            var root = query.from(aClass);
            query.select(root);
            query.orderBy(criteriaBuilder.desc(root.get(orderedBy)));
            var entities = session.createQuery(query).setFirstResult(0).setMaxResults(count).getResultList();
            transaction.commit();
            return (ArrayList<T>) entities;
        }
    }
}

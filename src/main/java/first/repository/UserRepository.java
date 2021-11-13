package first.repository;

import first.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IRepository<User> {
    SessionFactory sessionFactory;
    public Session session;
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session openSession(){
        session = sessionFactory.openSession();
        return session;
    }

    @Override
    public void Save(User entity) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void Delete(User entity) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void Update(User entity) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void UpdateOrAdd(User entity) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public User Get(long id) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        var user = session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }

    public ArrayList<User> GetAll(){
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        var criteriaBuilder = session.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(User.class);
        query.from(User.class);
        var users = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();
        return (ArrayList<User>)users;
    }
}

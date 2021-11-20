package first.database.repository;

import first.user.User;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class UserRepository extends Repository<User> {

    public UserRepository(Class<User> typeParameterClass, SessionFactory sessionFactory) {
        super(typeParameterClass, sessionFactory);
    }

    public ArrayList<User> getTop(int count) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();


            var criteriaBuilder = session.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(User.class);
            var root = query.from(User.class);
            query.select(root);
            query.orderBy(criteriaBuilder.desc(root.get("score")));
            var entities = session.createQuery(query).setFirstResult(0).setMaxResults(10).getResultList();

            transaction.commit();
            return (ArrayList<User>) entities;
        }
    }
}

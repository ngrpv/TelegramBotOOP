package first.repository;

import first.database.HibernateUtil;
import first.database.IDatabase;
import first.user.User;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class MainRepository implements IDatabase {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final UserRepository userRepository = new UserRepository(sessionFactory);


    @Override
    public void addUser(User user) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        userRepository.Save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User getUser(long id) {
        return userRepository.Get(id);
    }

    @Override
    public void updateOrAdd(User user) {
        userRepository.UpdateOrAdd(user);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return userRepository.GetAll();
    }
}

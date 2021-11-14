package first.repository;

import first.GameType;
import first.IGame;
import first.cowsAndBulls.CowsAndBullsState;
import first.database.HibernateUtil;
import first.database.IDatabase;
import first.hangman.HangmanGameState;
import first.user.User;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class HibernateDatabase implements IDatabase {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final IRepository<User> userRepository = new Repository<>(User.class, sessionFactory);
    private final IRepository<? extends IGame> hangmanRepository = new Repository<>(HangmanGameState.class, sessionFactory);
    private final IRepository<? extends IGame> cowsAndBullsRepository = new Repository<>(CowsAndBullsState.class, sessionFactory);


    @Override
    public void addUser(User user) {
        userRepository.Save(user);
        getGameRepository(user).Save(user.gameState);
    }

    @Override
    public User getUser(long id) {
        var user = userRepository.Get(id);
        user.gameState = getGameRepository(user).Get(user.GameID);
        return user;
    }

    @Override
    public void updateOrAdd(User user) {
        userRepository.UpdateOrAdd(user);
        getGameRepository(user).UpdateOrAdd(user.gameState);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        var users = userRepository.GetAll();
        for (var user :
                users) {
            setGame(user);
        }
        return users;
    }

    private void setGame(User user){
        user.gameState = getGameRepository(user).Get(user.GameID);
    }

    private IRepository<? extends IGame> getGameRepository(User user) {
        return switch (user.gameType) {
            case Hangman -> hangmanRepository;
            case CowsAndBulls -> cowsAndBullsRepository;
        };
    }
}

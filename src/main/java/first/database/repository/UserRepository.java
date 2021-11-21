package first.database.repository;

import first.database.IDatabase;
import first.user.User;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class UserRepository extends Repository<User> {
    private static GameStateRepository gameStateRepository;

    public UserRepository(IDatabase database) {
        super(User.class, database);
        gameStateRepository = new GameStateRepository(database);
    }

    @Override
    public User get(long id) {
        var user = super.get(id);
        if (user == null) return null;
        setGame(user);
        return user;
    }

    @Override
    public void save(User user) {
        super.save(user);
        gameStateRepository.save(user.gameState);
    }

    @Override
    public void saveOrUpdate(User user) {
        super.saveOrUpdate(user);
        gameStateRepository.saveOrUpdate(user.gameState);
    }

    @Override
    public ArrayList<User> getAll() {
        var users = super.getAll();
        for (var user :
                users) {
            setGame(user);
        }
        return users;
    }

    private void setGame(User user) {
        user.gameState = gameStateRepository.get(user.GameID, user.gameType);
    }

}

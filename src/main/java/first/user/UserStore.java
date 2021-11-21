package first.user;

import first.HibernateUtil;
import first.database.HibernateDatabase;
import first.database.repository.UserRepository;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class UserStore {
    private static ConcurrentHashMap<Long, User> userStates = new ConcurrentHashMap<>();
    private static final UserRepository userRepository = new UserRepository(new HibernateDatabase());

    public UserStore() {

    }

    public static User getUser(Long chatId) {
        updateDatabase();
        var user = tryGet(chatId);
        if (user == null) {
            user = new User(chatId);
            userStates.put(chatId, user);
        }
        return user;
    }

    public static void updateUserState(User user) {
        userRepository.saveOrUpdate(user);
    }

    private static void startDatabaseUpdater() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    updateDatabase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static User tryGet(Long id) {
        if (userStates.containsKey(id)) return userStates.get(id);
        var user = userRepository.get(id);
        if (user != null) {
            userStates.put(id, user);
        }
        return user;
    }

    public static ArrayList<User> getTop(int count) {
        return userRepository.getTop(count, "score");
    }


    public static ArrayList<User> getUsers() {
        return userRepository.getAll();
    }

    private static void updateDatabase() {
        for (var user : userStates.values()) {
            userRepository.saveOrUpdate(user);
        }
        userStates.clear();
    }
}

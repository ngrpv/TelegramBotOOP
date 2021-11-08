package first.user;

import com.sun.source.tree.SynchronizedTree;
import first.database.JsonConverter;
import first.database.PostgresDatabase;
import first.database.IDatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UserStore {
    private static final ConcurrentHashMap<Long, User> userStates = new ConcurrentHashMap<>();
    private static final IDatabase database = new JsonConverter("jsonUsers");
    // private static final IDatabase database = PostgresDatabase.tryGetDatabase();

    public UserStore() {
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(5000);
                        updateDatabase();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static Boolean userIsExists(Long chatId) {
        return tryGet(chatId) != null;
    }

    public static User getUserState(Long chatId) {
        var user = tryGet(chatId);
        if (user == null) {
            user = new User(chatId);
            userStates.put(chatId, user);
        }
        return user;
    }

    public static void updateUserState(User user) {
        if (database != null)
            database.updateOrAdd(user);
    }

    private static User tryGet(Long id) {
        if (userStates.containsKey(id)) return userStates.get(id);
        if (database == null) return null;
        return database.getUser(id);
    }

    private static void updateDatabase(){
        for (var user : userStates.values()) {
            database.updateOrAdd(user);
        }
    }

    public static ArrayList<User> getUsers() {
        return new ArrayList<>(userStates.values());
    }
}

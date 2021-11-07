package first.user;

import first.database.JsonConverter;
import first.database.PostgresDatabase;
import first.database.IDatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class UserStore {
    private static final HashMap<Long, User> userStates = new HashMap<>();
    private static final IDatabase database = new JsonConverter("jsonUsers");

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
    // private static final IDatabase database = PostgresDatabase.tryGetDatabase();

    public static Boolean userIsExists(Long chatId) {
        return tryGet(chatId) != null;
    }

    public static User getUserState(Long chatId) {
        var user = tryGet(chatId);
        if (user == null) {
            user = new User(chatId);
            if (database != null) {
                database.updateOrAdd(user);
            }
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

}

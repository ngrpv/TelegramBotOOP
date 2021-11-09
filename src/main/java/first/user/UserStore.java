package first.user;

import first.database.IDatabase;
import first.database.JsonConverter;
import first.database.PostgresDatabase;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class UserStore {
    private static final ConcurrentHashMap<Long, User> userStates = new ConcurrentHashMap<>();
   // private static final IDatabase database = new JsonConverter("jsonUsers");
    private static Boolean databaseUpdaterIsEnabled = false;
    private static final IDatabase database = PostgresDatabase.tryGetDatabase();


    public UserStore() {

    }

    public static User getUser(Long chatId) {
        if(!databaseUpdaterIsEnabled)
            startDatabaseUpdater();
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

    private static void startDatabaseUpdater() {
        databaseUpdaterIsEnabled = true;
        new Thread(new Runnable() {
            public void run() {
                while (true) {
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

    private static User tryGet(Long id) {
        if (userStates.containsKey(id)) return userStates.get(id);
        if (database == null) return null;
        var user = database.getUser(id);
        if(user!=null){
            userStates.put(id, user);
        }
        return user;
    }

    private static void updateDatabase() {
        for (var user : userStates.values()) {
            assert database != null;
            database.updateOrAdd(user);
        }
    }

    public static ArrayList<User> getUsers() {
        return database.getAllUsers();
    }
}

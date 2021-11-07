package first.user;

import first.database.Database;
import first.database.IDatabase;

import java.util.HashMap;

public class UserStore {
    private static final HashMap<Long, User> userStates = new HashMap<>();
    private static final IDatabase database = Database.tryGetDatabase();

    public static Boolean userIsExists(Long chatId) {
        return tryGet(chatId) != null;
    }

    public static User getUserState(Long chatId) {
        var user = tryGet(chatId);
        if(user == null){
            user = new User(chatId);
            userStates.put(chatId, user);
        }
        return user;
    }

    private static User tryGet(Long id){
        if(userStates.containsKey(id)) return userStates.get(id);
        if(database == null) return null;
        return database.getUser(id);
    }
}

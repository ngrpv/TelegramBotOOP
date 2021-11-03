package first.user;

import java.util.HashMap;

public class UserStore {
    private static final HashMap<Long, User> userStates = new HashMap<>();
    public static User getUserState(Long chatId) {
        User user;
        if (userStates.containsKey(chatId))
            user = userStates.get(chatId);
        else {
            user = new User(chatId);
            userStates.put(chatId, user);
        }
        return user;
    }
}

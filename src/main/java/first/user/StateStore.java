package first.user;

import java.util.HashMap;

public class StateStore {
    private static final HashMap<Long, User> userStates = new HashMap<>();

    public static User getUserState(Long chatId) {
        User user;
        if (userStates.containsKey(chatId))
            user = userStates.get(chatId);
        else {
            user = new User();
            userStates.put(chatId, user);
        }
        return user;
    }

    public static HashMap<Long, User> getUsers() {
        return userStates;
    }

}

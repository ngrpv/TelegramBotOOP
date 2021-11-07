package first.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
public class LeaderBoard {
    private static final HashMap<Long, User> userStates = StateStore.getUsers();

    public static String getLeaderBoard() {
        StringBuilder LeaderBoard = new StringBuilder("User statistics by the count of guessed words \n");
        ArrayList<User> users = new ArrayList<>(userStates.values());
        users.sort(Collections.reverseOrder(new User.GuessedWordComparator()));
        for (User user : users) {
            LeaderBoard.append("\n").append(user.getName()).append(":").append(user.getGuessedWord());
            if (users.size()>10) break;
        }
        return LeaderBoard.toString();

    }


}

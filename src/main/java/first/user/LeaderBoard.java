package first.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
public class LeaderBoard {
    private static final ArrayList<User> users = UserStore.getUsers();

    public static String getLeaderBoard() {
        StringBuilder LeaderBoard = new StringBuilder("User statistics by the count of guessed words \n");
        users.sort(Collections.reverseOrder(new User.GuessedWordComparator()));
        for (User user : users) {
            LeaderBoard.append("\n").append(user.getName()).append(":").append(user.getGuessedWord());
            if (users.size()>10) break;
        }
        return LeaderBoard.toString();

    }


}

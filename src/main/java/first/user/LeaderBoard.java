package first.user;

import java.util.ArrayList;
import java.util.Collections;
public class LeaderBoard {
    private static final ArrayList<User> users = UserStore.getUsers();

    public static String getLeaderBoard() {
        StringBuilder LeaderBoard = new StringBuilder("User statistics by the count of guessed words \n");
        users.sort(Collections.reverseOrder(new User.GuessedWordComparator()));
        for (User user : users) {
            if (users.size()>10) break;
            LeaderBoard.append("\n").append(user.getUserName()).append(":").append(user.getGuessedWords());
        }
        return LeaderBoard.toString();

    }


}

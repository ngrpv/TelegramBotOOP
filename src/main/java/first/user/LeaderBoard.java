package first.user;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoard {

    public static String getLeaderBoard() {
        ArrayList<User> users = UserStore.getTop(10);
        StringBuilder LeaderBoard = new StringBuilder("User statistics by the count of guessed words \n");
        for (User user : users) {
            LeaderBoard.append("\n").append(user.getUserName()).append(" : ").append(user.score);
        }
        return LeaderBoard.toString();
    }
}

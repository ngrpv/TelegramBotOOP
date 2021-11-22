package first.user;

import java.util.ArrayList;

public class LeaderBoard {

    public static String getLeaderBoard() {
        ArrayList<User> users = UserStore.getTop(10);
        StringBuilder LeaderBoard = new StringBuilder("Топ:\n");
        for (User user : users) {
            LeaderBoard.append("\n").append(user.getUserName()).append(" : ").append(user.score);
        }
        return LeaderBoard.toString();
    }
}

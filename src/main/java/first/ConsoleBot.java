package first;

import first.user.User;
import first.user.UserStore;

import java.util.Scanner;

public class ConsoleBot {
    public static User user = new User(0);

    public static void launch() {
        var stor = new UserStore();
        var user = UserStore.getUserState((long)0);
        var scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            var userMessage = scanner.nextLine();
            if (userMessage.length() == 0) continue;
            System.out.println(BotLogic.handleMessage(userMessage, user));
        }
        UserStore.updateUserState(user);
    }
}

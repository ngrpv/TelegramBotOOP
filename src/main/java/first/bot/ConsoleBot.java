package first.bot;

import first.user.User;
import first.user.UserStore;

import java.util.Scanner;

public class ConsoleBot {
    public static void launch() {
        var scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            var user = UserStore.getUser(0L);
            var userMessage = scanner.nextLine();
            if (userMessage.length() == 0) continue;
            System.out.println(BotLogic.handleMessage(userMessage, user));
            UserStore.updateUserState(user);
        }
    }
}

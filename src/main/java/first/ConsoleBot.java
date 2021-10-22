package first;

import java.util.Scanner;

public class ConsoleBot {
    public static User user = new User();

    public static void launch() {
        var scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            var userMessage = scanner.nextLine();
            if (userMessage.length() == 0) continue;
            System.out.println(BotLogic.handleMessage(userMessage, user));
        }
    }
}

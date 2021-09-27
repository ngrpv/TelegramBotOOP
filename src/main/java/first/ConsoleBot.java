package first;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleBot {
    public static UserState userState = new UserState(false);

    public static void launch() {
        var scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            var userMessage = scanner.nextLine();
            if (userMessage.length() == 0) continue;
            if (userMessage.equals("/exit")) break;
            System.out.println(BotLogic.getMessageForUser(userMessage, userState));
            }
        }
    }

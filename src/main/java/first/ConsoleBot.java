package first;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleBot {
    public static BotLogic bot = new BotLogic();
    public static HashMap<Long, UserState> userStates;
    private static Long chatId;
    public static void launchInConsole() {
        var scanner = new Scanner(System.in);
        var userState = bot.getUserState(chatId, BotLogic.hundlerType.Handler,userStates);
        while (scanner.hasNext()) {
            var userMessage = scanner.nextLine();
            if (userMessage.length() == 0) continue;
            if (userMessage.equals("/exit")) break;
            System.out.println(bot.getMessageForUser(userMessage, BotLogic.hundlerType.Handler,userState));
            }
        }
    }

package first;

import java.util.HashMap;
import java.util.Scanner;

public class BotHandler {
    public static ConsoleBot bot = new ConsoleBot();
    public static HashMap<Long, UserState> userStates;
    private static Long chatId;
    public static void launchInConsole() {
        var scanner = new Scanner(System.in);
        var userState = bot.getUserState(chatId, ConsoleBot.hundlerType.Handler,userStates);
        while (scanner.hasNext()) {
            var userMessage = scanner.nextLine();
            if (userMessage.length() == 0) continue;
            if (userMessage.equals("/exit")) break;
            System.out.println(bot.getMessageForUser(userMessage, ConsoleBot.hundlerType.Handler,userState));
            }
        }
    }

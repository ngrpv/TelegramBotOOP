import java.util.Scanner;

public class BotHandler {
    public static BotLogic bot = new BotLogic();
    public static void launchInConsole() {
        var scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            var userMessage = scanner.nextLine();
            if (userMessage.length() == 0) continue;
            if (userMessage.equals("exit")) break;
            System.out.println(bot.getMessageForUser(userMessage));
            }
        }
    }

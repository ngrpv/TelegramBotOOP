import java.util.Scanner;

public class BotLogic {
    public BotMessage message = new BotMessage();
    public String getMessageForUser(String userMessage)
    {
        var scanner = new Scanner(System.in);
        switch (userMessage)
        {
            case "help":
                return message.getHelp();
            case "start":
                return message.getStartGame();
            case "restart":
                return message.getRestartGame();
            default:
                return message.getUnknowCommand();
        }
    }
}

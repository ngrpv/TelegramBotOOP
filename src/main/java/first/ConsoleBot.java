package first;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Scanner;

public class ConsoleBot {
    public static User user = new User();

    public static void launch() {
        var scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            var userMessage = scanner.nextLine();
            if (userMessage.length() == 0) continue;
            if (userMessage.equals("/exit")) break;
            System.out.println(BotLogic.getMessageForUser(userMessage, user, new SendMessage()));
            }
        //Сохранение состояний юзеров
        //Кнопочки тг
        //Отделить состояние игры от логики

    }
    }

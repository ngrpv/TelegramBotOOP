package first.bot;

import first.user.User;

public class TGLogger {
    public static void fromUser(User user, String userMessage){
        System.out.printf("User: %s   \nMessage: %s%n", user.userName, userMessage);
    }

    public static void toUser(User user, String botAnswer){
        System.out.printf("User: %s   \nfrom Bot: %s%n", user.userName, botAnswer);
    }
}

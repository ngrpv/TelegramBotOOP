package first;

import java.util.HashMap;

public class BotLogic {
    private static String description = "Привет, друг! Висеца - игра в угадывания слов. Тебе загадывается слово, а ты должен его \n" +
            "угадать, используя буквы алфавита и возможность совершить ограниченное количество ошибок";
    private static String unknowCommand = "Извини, такой команды не существует!";
    private static String restartGame = "Игра перезапущена.";
    private static String help = "*  /hangman - запускает игру \n*  /restart - перезапускает игру \n*  /exit - выход";
    enum hundlerType
    {
        Telegram,
        Handler
    }

    public String getMessageForUser(String userMessage,hundlerType type,UserState userState) {
        if(userState !=null && userState.isPlaying && userMessage.length() == 1)
        {
            if (userState.gameState.isWin()) {
                userState.gameState.setWord();
            }
            return userState.gameState.checkAnswer(userMessage);
        }
        switch (userMessage) {
            case "/help":
                return description + "\n" + help;
            case "/hangman":
                //var game = userMessage.split(" ")[1];
                return getStartGame(userState,"Hangman");
            case "/restart":
                if (userState != null) {
                    userState.isPlaying = false;
                }
                return restartGame + "\n" + getStartGame(userState, "Hangman");
            case "/exit":
                return "exit";
            default:
                return unknowCommand;
        }
    }


    public String getStartGame(UserState states, String nameGame) {
        if (nameGame.equals("Hangman")) {
            return states.startPlaying();
        }
        else {
            return "Неизвестная игра";
        }
    }
    public UserState getUserState(Long chatId, hundlerType type, HashMap<Long, UserState> userStates) {
        UserState userState;
        if (type == hundlerType.Telegram) {
            if (userStates.containsKey(chatId))
                userState = userStates.get(chatId);
            else {
                userState = new UserState(chatId, false);
                userStates.put(chatId, userState);
            }
            return userState;
        }
        else
        {
            userState = new UserState(null,false);
            return userState;
        }
    }

}

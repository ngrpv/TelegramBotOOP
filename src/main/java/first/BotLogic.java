package first;

import java.util.HashMap;

public class BotLogic {
    private static final String DESCRIPTION = "Привет, друг! Виселица - игра в угадывания слов. Тебе загадывается слово, а ты должен его \n" +
            "угадать, используя буквы алфавита и возможность совершить ограниченное количество ошибок";
    private static final String UNKNOWN_COMMAND = "Извини, такой команды не существует!";
    private static final String RESTART_GAME = "Игра перезапущена.";
    private static final String HELP = "*  /hangman - запускает игру \n*  /restart - перезапускает игру \n*  /exit - выход";
    private static final HashMap<String, IGame> gameByName = new HashMap<>();

    public static String getMessageForUser(String userMessage, UserState userState) {
        if (userState != null && userState.isPlaying && userMessage.length() == 1) {
            if (userState.gameState.isWin()) {
                userState.gameState.setWord();
            }
            return userState.gameState.checkAnswer(userMessage);
        }
        switch (userMessage) {
            case "/help":
                return DESCRIPTION + "\n" + HELP;
            case "/hangman":
                return getStartGame(userState, "Hangman");
            case "/restart":
                if (userState != null) {
                    userState.isPlaying = false;
                }
                return RESTART_GAME + "\n" + getStartGame(userState, "Hangman");
            case "/exit":
                return "exit";
            default:
                return UNKNOWN_COMMAND;
        }
    }


    public static String getStartGame(UserState states, String nameGame) {
        if (nameGame.equals("Hangman")) {
            return states.startPlaying();
        } else {
            return "Неизвестная игра";
        }
    }
}
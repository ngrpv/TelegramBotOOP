package first;
import first.cowsAndBulls.CowsAndBullsState;
import first.hangman.HangmanGameState;

import java.util.HashMap;

public class BotLogic  {
    private static final String DESCRIPTION = "Привет, друг! Виселица - игра в угадывания слов. Тебе загадывается слово, а ты должен его \n" +
            "угадать, используя буквы алфавита и возможность совершить ограниченное количество ошибок";
    private static final String UNKNOWN_COMMAND = "Извини, такой команды не существует!";
    private static final String RESTART_GAME = "Игра перезапущена.";
    private static final String HELP = "*  /hangman - запускает игру Виселица \n*  /cowsAndBulls - запускает игру \"Быки и коровы\" \n*  /restart - перезапускает игру \n*  /exit - выход";
    private static final HashMap<String, IGame> gameByName = new HashMap<>();
    //todo getMessageForUser в зависимости от игры,т.е первый иф -  костыль, нужно исправить
    public static String getMessageForUser(String userMessage, UserState userState) {
        if (userState != null && userState.isPlaying) {
            return userState.gameState.checkAnswer(userMessage);
        }
        if(userState == null) return "userState is null";
        switch (userMessage) {
            case "/help":
                return DESCRIPTION + "\n" + HELP;
            case "/hangman" :
            case "виселица":
                return getStartGame(userState, new HangmanGameState());
            case "/cowsAndBulls":
                return getStartGame(userState, new CowsAndBullsState());
            case "/restart":
                userState.isPlaying = false;
                return RESTART_GAME + "\n" + "\n" + getStartGame(userState, new HangmanGameState());
            case "/exit":
                return "exit";
            default:
                return UNKNOWN_COMMAND;
        }
    }


    public static String getStartGame(UserState state, IGame game) {
            state.setGame(game);
            return state.startGame();
    }
}
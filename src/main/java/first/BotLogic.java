package first;

import first.cowsAndBulls.CowsAndBullsState;
import first.hangman.HangmanGameMessages;
import first.hangman.HangmanGameState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

public class BotLogic {
    private static final String DESCRIPTION = "Привет, друг! Данный бот позволяет сыграть в игры: Виселица, Быки и коровы. В будущем появятся новые игры";
    private static final String UNKNOWN_COMMAND = "Извини, такой команды не существует!";
    private static final String GAME_RESTARTED = "Игра перезапущена.";
    private static final String HELP = "*  /hangman - запускает игру Виселица \n*  /restart - перезапускает игру \n*  /exit - выход";
    private static final HashMap<String, IGame> gameByName = new HashMap<>();

    public static String handleMessage(String userMessage, User user) {
        if (user == null) return "userState is null";
        switch (userMessage) {
            case "/help":
            case "Помощь":
                return DESCRIPTION + "\n" + HELP;
            case "Правила":
                return user.gameState.getRules();
            case "/hangman":
            case "Виселица":
                return startGame(user, new HangmanGameState());
            case "/cowsAndBulls":
            case "Быки и коровы":
                return startGame(user, new CowsAndBullsState());
            case "Перезапустить":
            case "/restart":
                return GAME_RESTARTED + "\n" + "\n" + restartGame(user);
            case "/exit":
            case "Выход":
                user.changeState(UserState.onMenu);
                return "Меню";
            default:
                if (user.isPlaying()) {
                    return user.gameState.checkAnswer(userMessage);
                }
                return UNKNOWN_COMMAND;
        }
    }

    public static String restartGame(User user) {
        user.changeState(UserState.Playing);
        return user.gameState.getStartMessage();
    }

    public static String startGame(User state, IGame game) {
        state.changeGame(game);
        state.changeState(UserState.Playing);
        return state.gameState.getStartMessage();
    }
}
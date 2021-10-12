package first;
import first.cowsAndBulls.CowsAndBullsState;
import first.hangman.HangmanGameMessages;
import first.hangman.HangmanGameState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

public class BotLogic  {
    private static final String DESCRIPTION = "Привет, друг! Виселица - игра в угадывания слов. Тебе загадывается слово, а ты должен его \n" +
            "угадать, используя буквы алфавита и возможность совершить ограниченное количество ошибок";
    private static final String UNKNOWN_COMMAND = "Извини, такой команды не существует!";
    private static final String RESTART_GAME = "Игра перезапущена.";
    private static final String HELP = "*  /hangman - запускает игру \n*  /restart - перезапускает игру \n*  /exit - выход";
    private static final HashMap<String, IGame> gameByName = new HashMap<>();

    public static String getMessageForUser(String userMessage, UserState userState, SendMessage sendMessage) {
        if (userState != null && userState.isPlaying && userMessage.length() == 1) {
            return userState.gameState.checkAnswer(userMessage);
        }




        if(userState == null) return "userState is null";
        switch (userMessage) {
            case "/help":
            case "Помощь":
                return DESCRIPTION + "\n" + HELP;
            case "/hangman":
            case "Виселица":
                return getStartGame(userState, new HangmanGameState());
            case "/cowsAndBulls":
                return getStartGame(userState, new CowsAndBullsState());
            case "Начать заново":
                userState.isPlaying = false;
                return RESTART_GAME + "\n" + "\n" + getStartGame(userState, new HangmanGameState());
            case "/exit":
                return "exit";
            default:
                return UNKNOWN_COMMAND;
        }
    }


    public static String getStartGame(UserState state, IGame game) {
            state.changeGame(game);
            return state.startGame();
    }
}
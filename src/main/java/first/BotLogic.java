package first;
import first.cowsAndBulls.CowsAndBullsState;
import first.hangman.HangmanGameMessages;
import first.hangman.HangmanGameState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

public class BotLogic  {
    private static final String DESCRIPTION = "Привет, друг! Данный бот позволяет сыграть в игры: Виселица, Быки и коровы. В будущем появятся новые игры";
    private static final String UNKNOWN_COMMAND = "Извини, такой команды не существует!";
    private static final String GAME_RESTARTED = "Игра перезапущена.";
    private static final String HELP = "*  /hangman - запускает игру Виселица \n*  /restart - перезапускает игру \n*  /exit - выход";
    private static final HashMap<String, IGame> gameByName = new HashMap<>();
    //todo getMessageForUser в зависимости от игры,т.е первый иф -  костыль, нужно исправить
    public static String getMessageForUser(String userMessage, User user, SendMessage sendMessage) {
        if (user != null && user.isPlaying() && userMessage.length() == 1) {
            return user.gameState.checkAnswer(userMessage);
        }

        if(user == null) return "userState is null";
        switch (userMessage) {
            case "/help":
            case "Помощь":
                return DESCRIPTION + "\n" + HELP;
            case "/hangman":
            case "Виселица":
                return startGame(user, new HangmanGameState());
            case "/cowsAndBulls":
                return startGame(user, new CowsAndBullsState());
            case "Перезапустить":
            case "/restart":
                return GAME_RESTARTED + "\n" + "\n" + user.startGame();
            case "/exit":
                user.changeState(UserState.onMenu);
                return "Меню";
            default:
                return UNKNOWN_COMMAND;
        }
    }


    public static String startGame(User state, IGame game) {
            state.changeGame(game);
            state.changeState(UserState.Playing);
            return state.gameState.getStartMessage();
    }
}
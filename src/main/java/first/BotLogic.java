package first;

import first.cowsAndBulls.CowsAndBullsState;
import first.hangman.HangmanGameState;
import first.user.User;
import first.user.UserState;

public class BotLogic {
    private static final String DESCRIPTION = "Привет, друг! Данный бот позволяет сыграть в игры:\nВиселица, Быки и коровы.";
    private static final String UNKNOWN_COMMAND = "Извини, такой команды не существует!";
    private static final String GAME_RESTARTED = "Игра перезапущена.";

    public static String handleMessage(String userMessage, User user) {
        switch (userMessage) {
            case "/help":
            case "Помощь":
                return DESCRIPTION;
            case "Правила":
                return user.gameState.getRules();
            case "Перезапустить":
            case "/restart":
                return GAME_RESTARTED + "\n" + "\n" + startGame(user);
            case "/exit":
            case "Выход":
                user.changeState(UserState.onMenu);
                return "Меню";
            case "/cowsAndBulls":
            case "Быки и коровы":
                if(!user.isPlaying())
                    return startGame(user, new CowsAndBullsState());
            case "/hangman":
            case "Виселица":
                if(!user.isPlaying())
                    return startGame(user, new HangmanGameState());
            default:
                if (user.isPlaying()) {
                    return user.gameState.checkAnswer(userMessage);
                }
                return UNKNOWN_COMMAND;
        }
    }

    private static void setGame(User user, IGame game) {
        user.changeGame(game);
    }

    private static String startGame(User user) {
        user.changeState(UserState.Playing);
        return user.gameState.getStartMessage();
    }

    private static String startGame(User state, IGame game) {
        setGame(state, game);
        return startGame(state);
    }
}
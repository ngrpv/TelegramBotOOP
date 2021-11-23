package first.bot;

import first.games.GameType;
import first.games.hangman.HangmanGameState;
import first.user.LeaderBoard;
import first.user.User;
import first.user.UserState;


public class BotLogic {
    private static final String DESCRIPTION = "Привет, друг! Данный бот позволяет сыграть в игры:\nВиселица, Быки и коровы.";
    private static final String UNKNOWN_COMMAND = "Такой команды не существует!";
    private static final String GAME_RESTARTED = "Игра перезапущена.";
    private static final String USER_NAME_INPUT = "Введите своё имя";


    public static String handleMessage(String userMessage, User user) {
        if (user.userName == null && user.flagName) {
            user.flagName = false;
            return USER_NAME_INPUT;
        }
        switch (userMessage) {
            case "/help":
            case "Помощь":
                return DESCRIPTION;
            case "Правила":
                return user.gameState.getRules();
            case "/LeaderBoard":
            case "Топ":
                if (!user.isPlaying())
                    return LeaderBoard.getLeaderBoard();
            case "Перезапустить":
            case "/restart":
                return GAME_RESTARTED + "\n" + "\n" + startGame(user);
            case "/exit":
            case "Выход":
                if (user.isPlaying()) {
                    user.score += user.gameState.getGuessedWords();
                }
                user.changeGame(GameType.Hangman);
                user.changeState(UserState.OnMenu);
                return "Меню";
            case "/cowsAndBulls":
            case "Быки и коровы":
                if (!user.isPlaying())
                    return startGame(user, GameType.CowsAndBulls);
            case "/hangman":
            case "Виселица":
                if (!user.isPlaying())
                    return startGame(user, GameType.Hangman);
            default:
                if (user.userName == null) {
                    user.userName = userMessage;
                    return "Имя установлено: " + userMessage;
                }
                if (user.isPlaying()) {
                    return user.gameState.checkAnswer(userMessage);
                }
        }
        return UNKNOWN_COMMAND;
    }

    private static void setGame(User user, GameType gameType) {
        user.changeGame(gameType);
    }

    private static String startGame(User user) {
        user.changeState(UserState.Playing);
        return user.gameState.getStartMessage();
    }

    private static String startGame(User state, GameType gameType) {
        setGame(state, gameType);
        return startGame(state);
    }
}

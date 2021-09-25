public class BotLogic {
    private static String help = "Привет, друг! Висеца - игра в угадывания слов. Тебе загадывается слово, а ты должен его \n" +
            "угадать, используя буквы алфавита и возможность совершить ограниченное количество ошибок";
    private static String unknowCommand = "Извини, такой команды не существует!";
    private static String restartGame = "Игра перезапущена.";
    public static UserState userState;

    public String getMessageForUser(String userMessage) {
        if( userState !=null && userState.isPlaying && userMessage.length()==1)
        {
            return userState.gameState.checkAnswer(userMessage);
        }
        switch (userMessage) {
            case "help":
                return help;
            case "start Hangman":
                var game = userMessage.split(" ")[1];
                userState = new UserState(null, true);
                return getStartGame(userState,game);
            case "restart":
                userState.isPlaying = false;
                return restartGame + "\n" + getStartGame(userState, "HangmanGame");
            case "exit":
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

}

public class BotMessage {

    private static String help = "Висеца - игра в угадывания слов. Тебе загадывается слово, а ты должен его \n" +
            "угадать, используя буквы алфавита и возможность совершить ограниченное количество ошибок";
    private static String unknowCommand = "Извини, такой команды не существует!";
    private static String restartGame = "Игра перезапущена.";
    private static String startGame = "Игра запущена";

    public String getUnknowCommand(){
        return unknowCommand;
    }
    public String getHelp(){
        return help;
    }
    public String getRestartGame(){
        return restartGame;
    }
    public String getStartGame(){
        return startGame;
    }






}

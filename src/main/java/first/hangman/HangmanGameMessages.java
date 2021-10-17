package first.hangman;

public class HangmanGameMessages {

    private static final String WIN_TEXT = "Ты выиграл!";
    private static final String LOSE_TEXT = "Ты проиграл((";
    private static final String ALREADY_USED_LETTER = "Буква была введена ранее, введите другую!";
    private static final String ONLY_ONE_LETTER = "Ты должен написать только одну букву!";
    private static final String RULES = "asdsd";

    public static String getRules() {return RULES;}


    protected static String getMessageForUser(HangmanGameAnswerEnum checkResult, HangmanGameState gameState) {

        switch (checkResult) {
            case NOT_LETTER:
                return ONLY_ONE_LETTER;
            case WIN:
                var guessedWord = gameState.getWordWithGuessedLetters();
                gameState.setWord();
                 return String.format("%s\n%s\n\nНовое слово:\n%s", guessedWord, WIN_TEXT, gameState.getHiddenWord());
            case LOSE:
                return LOSE_TEXT;
            case ALREADY_USED_LETTER: {
                return ALREADY_USED_LETTER;
            }
        }
        return String.format("%s\nОсталось жизней: %d", gameState.getWordWithGuessedLetters(), gameState.getHealthPoints());
    }

    //todo: добавить тик игры, выделить все общие методы в интерфейс, реализовать новую игру, паттер стратегия

}


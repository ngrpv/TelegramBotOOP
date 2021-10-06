package first.hangman;

public class HangmanGameLogic {

    private static final String WIN_TEXT = "Ты выиграл!";
    private static final String LOSE_TEXT = "Ты проиграл((";
    private static final String ALREADY_USED_LETTER = "Буква была введена ранее, введите другую!";
    private static final String ONLY_ONE_LETTER = "Ты должен написать только одну букву!";


    public static String checkAnswer(String answer, HangmanGameState gameState) {
        if (answer.length() != 1) return ONLY_ONE_LETTER;
        var userChar = answer.toLowerCase().charAt(0);
        var checkResult = gameState.checkAnswer(userChar);
        switch (checkResult) {
            case NOT_LETTER:
                return ONLY_ONE_LETTER;
            case WIN:
                return gameState.getWordWithGuessedLetters() + "\n" + WIN_TEXT;
            case LOSE:
                return LOSE_TEXT;
            case ALREADY_USED_LETTER: {
                return ALREADY_USED_LETTER;
            }
        }
        return gameState.getWordWithGuessedLetters() + "\n" + "Осталось жизней: " + gameState.getHealthPoints();
    }


}


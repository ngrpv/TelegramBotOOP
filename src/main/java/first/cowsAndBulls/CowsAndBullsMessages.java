package first.cowsAndBulls;

import first.hangman.HangmanGameAnswerEnum;
import first.hangman.HangmanGameState;

class CowsAndBullsMessages {

    private static final String HELP = "help msg for CAB game";
    private static final String WORD_EQUAL_LENGTH = "Введите слово той же длины, что и загадано!";
    private static final String ALREADY_USED_WORD = "Слово было введено ранее, введите другое!";
    private static final String WIN_TEXT = "Ты выиграл!";
    private static final String NEW_WORD_TEXT = "Новое слово загадано!";
    private static final String UNKNOWN_COMMAND = "Неизвестная комманда";
    private static final String RULES = "Неизвестная комманда";


    public static String getRules() {
        return getRules();
    }
    protected static String getMessageForUser(CowsAndBullsEnum checkResult, CowsAndBullsState gameState)
    {
        switch (checkResult)
        {
            case WRONG_WORD:
                return WORD_EQUAL_LENGTH;
            case ALREADY_USED_WORD:
                return ALREADY_USED_WORD;
            case WIN:
                gameState.setWord();
                return WIN_TEXT + "\n" + NEW_WORD_TEXT;
        }
        return UNKNOWN_COMMAND;
    }

    protected static String getMessageForUser(int cows, int bulls,CowsAndBullsState gameState)
    {
        return String.format("Быки:%s\nКоровы:%s",bulls,cows);
    }
}

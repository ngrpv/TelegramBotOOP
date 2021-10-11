package first.cowsAndBulls;

import first.FileHandler;
import first.IGame;
import first.IWordParser;

import java.lang.reflect.Field;

public class CowsAndBullsState implements IGame {
    private static final String fileName = "hangmanWords.txt";
    private static IWordParser wordParser;
    private String word;

    public CowsAndBullsState() {
        wordParser = FileHandler.getParser(fileName);
    }

    @Override
    public String checkAnswer(String answer) {
        return "not implemented";
    }

    @Override
    public String getStartMessage() {
        return "not implemented";
    }

    @Override
    public void start() {

    }
}

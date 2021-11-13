package first.cowsAndBulls;

import first.IGame;
import first.IWordParser;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Locale;

public class CowsAndBullsState implements IGame {
    private static IWordParser wordParser;
    private String word;
    private int guessedWords;


    public CowsAndBullsState() {
        this(new CowsAndBullsWordParser());
    }

    public CowsAndBullsState(IWordParser wordParser) {
        CowsAndBullsState.wordParser = wordParser;
    }

    @Override
    public void start() {
        setWord(wordParser);
        System.out.println(word);
    }

    @Override
    public String getRules() {
        return CowsAndBullsMessages.getRules();
    }

    public void setWord() {
        setWord(wordParser);
    }

    public void setWord(IWordParser parser) {
        setWord(parser.getWord());
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getGuessedWords() {return guessedWords; }

    @Override
    public String checkAnswer(String answer) {
        if (answer.length() < word.length() || answer.length() > word.length() + 1)
            return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.WRONG_WORD, this);
        var usedWord = answer.toLowerCase(Locale.ROOT);
        var cowsAndBullsValue = getCowsAndBulls(usedWord, word);
        if (word.length() == cowsAndBullsValue[1] && answer.length() == word.length()) {
            guessedWords+=1;
            return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.WIN, this) + "\n\n" + getStartMessage();
        }
        return CowsAndBullsMessages.getMessageForUser(cowsAndBullsValue[0], cowsAndBullsValue[1], this);
    }

    public int[] getCowsAndBulls(String userWord, String word) {
        HashSet<Integer> bullsPositions = new HashSet<>();
        HashSet<Integer> cowsPositions = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == userWord.charAt(i)) {
                bullsPositions.add(i);
            }
        }
        for (int i = 0; i < userWord.length(); i++) {
            if (!bullsPositions.contains(i))
                findCows(bullsPositions, cowsPositions, userWord.charAt(i));

        }
        return new int[]{cowsPositions.size(), bullsPositions.size()};
    }

    private void findCows(HashSet<Integer> bulls, HashSet<Integer> cows, char ch) {
        for (int i = 0; i < word.length(); i++) {
            if (bulls.contains(i) || cows.contains(i)) continue;
            if (ch == word.charAt(i)) {
                cows.add(i);
                break;
            }
        }
    }

    @Override
    public String getStartMessage() {
        return String.format("Количеcтво цифр: %s", word.length());
    }
}
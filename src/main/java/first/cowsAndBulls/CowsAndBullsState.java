package first.cowsAndBulls;

import first.IGame;
import first.IWordParser;

import java.util.Locale;


public class CowsAndBullsState implements IGame {
    private static final String fileName = "hangmanWords.txt"; // todo unused
    private static IWordParser wordParser;
    private String word;


    public CowsAndBullsState() {
        this(new CowsAndBullsWordParser());
    }

    public CowsAndBullsState(IWordParser wordParser) {
        CowsAndBullsState.wordParser = wordParser;
        start();
    }

    @Override
    public void start() {
        setWord(wordParser);
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

    @Override
    public String checkAnswer(String answer) {
        if (answer.length() < word.length() || answer.length() > word.length() + 1)
            return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.WRONG_WORD, this);
        var usedWord = answer.toLowerCase(Locale.ROOT);
        var cowsAndBullsValue = getCowsAndBulls(usedWord, word);
        if (word.length() == cowsAndBullsValue[1] && answer.length() == word.length()) {
            return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.WIN, this);
        }
        return CowsAndBullsMessages.getMessageForUser(cowsAndBullsValue[0], cowsAndBullsValue[1], this);
    }

    private int countBetween(char letter, String word, int to) {
        var count = 0;
        for (int i = 0; i < to; i++) {
            if (word.charAt(i) == letter)
                count += 1;
        }
        return count;
    }

    private int[] getCowsAndBulls(String userWord, String word) {
        var bulls = 0;
        var cows = 0;

        StringBuilder userWordBuilder = new StringBuilder(userWord);
        StringBuilder wordBuilder = new StringBuilder(word);

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == userWord.charAt(i)) {
                bulls += 1;
                userWordBuilder.setCharAt(i, ' ');
                wordBuilder.setCharAt(i, ' ');
            }
        }
        if (bulls != 0) {
            userWord = wordBuilder.toString();
            word = userWordBuilder.toString();
        }
        for (int i = 0; i < userWord.length(); i++) {
            if (userWord.charAt(i) == ' ')
                continue;
            var countLeft = countBetween(userWord.charAt(i), userWord, i);
            var countInUserWord = countBetween(
                    userWord.charAt(i), word, word.length());
            if (countInUserWord != 0
                    && countLeft < countInUserWord)
                cows++;
        }
        return new int[]{cows, bulls};
    }

    @Override
    public String getStartMessage() {
        return String.format("В этом числе %s цифр", word.length());
    }
}
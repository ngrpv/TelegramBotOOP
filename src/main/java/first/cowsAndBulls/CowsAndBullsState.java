package first.cowsAndBulls;

import first.FileHandler;
import first.IGame;
import first.IWordParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class CowsAndBullsState implements IGame {
    private static final String fileName = "hangmanWords.txt";
    private static IWordParser wordParser;
    private String word;
    private String secretWord;
    private Integer cows;
    private Integer bulls;
    private Boolean gameIsOver;
    private HashSet<String> usedWords;
    private List<Character> wordCharacterList;


    public CowsAndBullsState() {
        this(FileHandler.getParser(fileName));
    }

    public CowsAndBullsState(IWordParser wordParser) {
        CowsAndBullsState.wordParser = wordParser;
        start();
    }

    @Override
    public void start() {
        setWord(wordParser);
    }

    public void setWord() {
        setWord(wordParser);
    }

    public void setWord(IWordParser parser) {
        setWord(parser.getWord());
    }

    public void setWord(String word) {
        this.word = word;
        updateState();
    }
    public String getWord() {
        return word;
    }

    public int getCows() {
        return cows;
    }

    public int getBulls() {
        return bulls;
    }

    @Override
    public Boolean isWin() {
        return secretWord.length() == bulls;
    }

    @Override
    public String checkAnswer(String answer) {
        if (answer.length() != word.length())
            return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.WRONG_WORD, this);
        var usedWord = answer.toLowerCase(Locale.ROOT);
        if (usedWords.contains(usedWord)) {
            return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.ALREADY_USED_WORD, this);
        }
        usedWords.add(usedWord);
        var userWordList = getListByWordChars(usedWord);
        updateCowsAndBulls();
        getCowsAndBulls(userWordList);
        if (isWin()) {
            return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.WIN, this);
        }
        return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.CORRECT_WORD, this);
    }

    private void getCowsAndBulls(List<Character> userWordList) {
        for (int i = 0; i < userWordList.size(); i++) {
            if (wordCharacterList.contains(userWordList.get(i))) {
                if (userWordList.get(i).equals(wordCharacterList.get(i))) {
                    bulls += 1;
                } else {
                    cows += 1;
                }
            }
        }
    }

    @Override
    public String getStartMessage() {
        return "not implemented";
    }

    private void updateState() {
        wordCharacterList = getListByWordChars(word);
        secretWord = word;
        cows = 0;
        bulls = 0;
        gameIsOver = false;
        usedWords = new HashSet<>();
    }
    private void updateCowsAndBulls()
    {
        cows = 0;
        bulls = 0;
    }

    private List<Character> getListByWordChars(String word) {
        var characters = new ArrayList<Character>();
        for (Character ch : word.toLowerCase().toCharArray()) {
            characters.add(ch);
        }
        return characters;
    }
}

package first.cowsAndBulls;

import first.FileHandler;
import first.IGame;
import first.IWordParser;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class CowsAndBullsState implements IGame {
    private static final String fileName = "hangmanWords.txt";
    private static IWordParser wordParser;
    private String word;
    private Character[] wordCharacterList;
    private HashSet<Character> wordHashSet;


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

    @Override
    public Boolean isWin() {
        return word.length() ==2;
    }

    @Override
    public String checkAnswer(String answer) {
        if (answer.length() < word.length() || answer.length()>word.length()+1 || !checkUniqueChars(answer))
            return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.WRONG_WORD, this);
        var usedWord = answer.toLowerCase(Locale.ROOT);
        var userWordList = getListByWordChars(usedWord);
        var cowsAndBullsValue = getCowsAndBulls(userWordList);
        if (word.length()==cowsAndBullsValue[1] && answer.length() == word.length()) {
            return CowsAndBullsMessages.getMessageForUser(CowsAndBullsEnum.WIN, this);
        }
        return CowsAndBullsMessages.getMessageForUser(cowsAndBullsValue[0],cowsAndBullsValue[1], this);
    }

    private int[] getCowsAndBulls(Character[] userWordList) {
        var bulls = 0;
        var cows = 0;
        for (int i = 0; i < wordCharacterList.length; i++) {
            if (ArrayUtils.contains(userWordList,wordCharacterList[i])) {
                if (userWordList[i].equals(wordCharacterList[i])){
                    bulls += 1;
                } else {
                    cows += 1;
                }
            }
        }
        return new int[] {cows,bulls};
    }

    @Override
    public String getStartMessage() {
        return String.format("В этом слове %s букв",word.length());
    }

    private void updateState() {
        wordCharacterList = getListByWordChars(word);
    }


    private Character[] getListByWordChars(String word) {
        var characters = new ArrayList<Character>();
        for (Character ch : word.toLowerCase().toCharArray()) {
            characters.add(ch);
        }
        return characters.toArray(new Character[0]);
    }


    private static boolean checkUniqueChars(String s) {
        final Set<Character> chars = new HashSet<>();
        for (final char c : s.toCharArray()) {
            if (chars.contains(c)) {
                return false;
            }
            chars.add(c);
        }
        return true;
    }
}
// TODO: 13.10.2021 заменить лист на массив, добавить возможность вводить слова с +1 буквой 
// TODO: 13.10.2021 добавить перегрузку метода getusermessage(c скоровами и без), прописать хелпы, ссылка на расширенные правила

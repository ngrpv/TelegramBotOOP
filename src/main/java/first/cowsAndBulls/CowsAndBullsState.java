package first.cowsAndBulls;

import first.IGame;
import first.IWordParser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Locale;
@Entity
@Table(name = "cows_and_bulls")
@Getter @Setter
public class CowsAndBullsState implements IGame {
    private static IWordParser wordParser;
    private String word;
    @Setter
    private int guessedWords;
    @Id
    private Long id;


    public CowsAndBullsState(Long id) {
        this(new CowsAndBullsWordParser());
        this.id = id;
    }

    public CowsAndBullsState(IWordParser wordParser) {
        CowsAndBullsState.wordParser = wordParser;
    }

    public CowsAndBullsState() {

    }

    @Override
    public void start() {
        setWord(wordParser);
        System.out.println(word);
    }

    @Override
    @Transient
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
    @Transient
    public String getStartMessage() {
        return String.format("Количеcтво цифр: %s", word.length());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
package first.games.hangman;

import first.FileHandler;
import first.games.GameType;
import first.games.IGame;
import first.IWordParser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "hangman_game")
@Getter
@Setter
@NamedEntityGraph
public class HangmanGameState implements IGame {
    private String word;
    @ElementCollection(fetch = FetchType.EAGER)
    public Set<Character> wordHashSet;
    @ElementCollection(fetch = FetchType.EAGER)
    public Set<Character> guessedLetters;
    @ElementCollection(fetch = FetchType.EAGER)
    public Set<Character> usedLetters;
    private static final String fileName = "hangmanWords.txt";
    private static IWordParser wordParser = new FileHandler(fileName);
    private int healthPoints;
    private int guessedWords;
    @Id
    private Long id;

    public HangmanGameState(Long id) {

        this(new FileHandler(fileName));
        this.id = id;
    }

    public HangmanGameState(IWordParser wordParser) {
        HangmanGameState.wordParser = wordParser;
        start();
    }

    public HangmanGameState(String word, HashSet<Character> wordHashSet, HashSet<Character> guessedLetters, HashSet<Character> usedLetters, int healthPoints) {
        this.word = word;
        this.wordHashSet = wordHashSet;
        this.guessedLetters = guessedLetters;
        this.usedLetters = usedLetters;
        this.healthPoints = healthPoints;
    }

    public HangmanGameState() {

    }

    public void start() {
        setWord(wordParser);
    }

    @Override
    public String getRules() {
        return HangmanGameMessages.getRules();
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

    public Boolean isWin() {
        return wordHashSet.size() == guessedLetters.size();
    }

    public Boolean isOver() {
        return healthPoints <= 0;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public Integer getGuessedWords() {
        return guessedWords;
    }

    @Override
    public GameType getType() {
        return GameType.Hangman;
    }

    public String checkAnswer(String answer) {
        if (isOver()) {
            return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.LOSE, this);
        }
        if (answer.length() != 1)
            return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.NOT_ONE_LETTER, this);
        var userLetter = answer.toLowerCase(Locale.ROOT).charAt(0);
        if (usedLetters.contains(userLetter)) {
            return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.ALREADY_USED_LETTER, this);
        }
        usedLetters.add(userLetter);
        if (wordHashSet.contains(userLetter)) {
            guessedLetters.add(userLetter);
            if (isWin()) {
                guessedWords += 1;
                return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.WIN, this);
            }
            return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.CORRECT_LETTER, this);
        } else {
            healthPoints--;
            if (isOver()) {
                return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.LOSE, this) + restartGame();
            }
            return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.WRONG_LETTER, this);
        }
    }

    private String restartGame() {
        start();
        return "\nИгра перезапущена\n\n" + getStartMessage();
    }


    @Override
    public String getStartMessage() {
        return getHiddenWord();
    }

    private void updateState() {
        wordHashSet = getHashSetByWordChars(word);
        guessedLetters = new HashSet<>();
        usedLetters = new HashSet<>();
        healthPoints = 6;
    }

    private HashSet<Character> getHashSetByWordChars(String word) {
        var characters = new HashSet<Character>();
        for (Character ch : word.toLowerCase().toCharArray()) {
            characters.add(ch);
        }
        return characters;
    }

    public String getWordWithGuessedLetters() {
        var resultStr = new StringBuilder();
        for (Character ch : word.toCharArray()) {
            if (guessedLetters.contains(ch.toString().toLowerCase(Locale.ROOT).charAt(0))) {
                resultStr.append(ch).append(" ");
            } else {
                resultStr.append("* ");
            }
        }
        return resultStr.toString();
    }

    protected String getHiddenWord() {
        return getWordWithGuessedLetters();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

package first.hangman;

import first.IWordParser;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Locale;

public class HangmanGameState {
    private String word;
    private HashSet<Character> wordHashSet;
    private HashSet<Character> guessedLetters;
    private HashSet<Character> usedLetters;

    private IWordParser wordParser;
    private static final String WIN_TEXT = "Ты выиграл!";
    private static final String LOSE_TEXT = "Ты проиграл((";
    private static final String ALREADY_USED_LETTER = "Буква была введена ранее, введите другую!";
    private static final String ONLY_ONE_LETTER = "Ты должен написать только одну букву!";
    private Boolean gameIsOver;
    private int healthPoints;

    public HangmanGameState(IWordParser wordParser) throws FileNotFoundException {
        this.wordParser = wordParser;
        setWord();
    }

    public void setWord() {
        setWord(wordParser.getNextWord());
    }

    public void setWord(String word) {
        this.word = word;
        updateState();
    }

    private void updateState() {
        wordHashSet = getHashSetByWordChars(word);
        guessedLetters = new HashSet<>();
        usedLetters = new HashSet<>();
        healthPoints = 6;
        gameIsOver = false;
    }

    public String getWord() {
        return word;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public String checkAnswer(String answer) {
        if (answer.length() != 1) return ONLY_ONE_LETTER;
        var userChar = answer.toLowerCase().charAt(0);
        if (usedLetters.contains(userChar)) {
            return ALREADY_USED_LETTER;
        }
        usedLetters.add(userChar);
        if (wordHashSet.contains(userChar))
            guessedLetters.add(userChar);
        else healthPoints--;
        if (isWin()) {
            return getWordWithGuessedLetters() + "\n" + WIN_TEXT;
        }
        if (healthPoints == 0 || gameIsOver) {
            gameIsOver = true;
            return LOSE_TEXT;
        }
        return getWordWithGuessedLetters() + "\n" + "Осталось жизней: " + healthPoints;
    }

    public Boolean isWin() {
        return wordHashSet.size() == guessedLetters.size();
    }

    public Boolean isOver() {
        return gameIsOver;
    }

    public String getHiddenWord() {
        return getWordWithGuessedLetters();
    }

    private String getWordWithGuessedLetters() {
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

    private HashSet<Character> getHashSetByWordChars(String word) {
        var characters = new HashSet<Character>();
        for (Character ch : word.toLowerCase().toCharArray()) {
            characters.add(ch);
        }
        return characters;
    }


}


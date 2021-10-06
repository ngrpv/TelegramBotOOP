package first.hangman;

import first.IWordParser;

import java.util.HashSet;
import java.util.Locale;

public class HangmanGameState {
    private String word;

    private HashSet<Character> wordHashSet;

    private HashSet<Character> guessedLetters;
    private HashSet<Character> usedLetters;
    private Boolean gameIsOver;

    private int healthPoints;

    private HangmanGameStateEnum gameStateEnum;
    private IWordParser wordParser;

    public HangmanGameState(IWordParser wordParser) {
        this.wordParser = wordParser;
        setWord();
    }

    public void setWord() {
        setWord(wordParser.getNextWord());
    }

    public String getWord() {
        return word;
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

    public HashSet<Character> getUsedLetters() {
        return usedLetters;
    }

    public void addUsedLetter(Character letter) {
        usedLetters.add(letter);
    }

    public HashSet<Character> getWordHashSet() {
        return wordHashSet;
    }

    public HangmanGameStateEnum checkAnswer(Character userLetter) {
        if (usedLetters.contains(userLetter)) {
            return HangmanGameStateEnum.ALREADY_USED_LETTER;
        }
        usedLetters.add(userLetter);
        if (wordHashSet.contains(userLetter)) {
            guessedLetters.add(userLetter);
            if (isWin()) {
                return HangmanGameStateEnum.WIN;
            }
            return HangmanGameStateEnum.CORRECT_LETTER;
        } else {
            healthPoints--;
            if (healthPoints <= 0) {
                gameIsOver = true;
                return HangmanGameStateEnum.LOSE;
            }
            return HangmanGameStateEnum.WRONG_LETTER;
        }
    }

    private void updateState() {
        wordHashSet = getHashSetByWordChars(word);
        guessedLetters = new HashSet<>();
        usedLetters = new HashSet<>();
        healthPoints = 6;
        gameIsOver = false;
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

    public String getHiddenWord() {
        return getWordWithGuessedLetters();
    }
}

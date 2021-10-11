package first.hangman;

import first.FileHandler;
import first.IWordParser;

import java.util.HashSet;
import java.util.Locale;

public class HangmanGameState {
    private String word;

    private HashSet<Character> wordHashSet;

    private HashSet<Character> guessedLetters;
    private HashSet<Character> usedLetters;
    private Boolean gameIsOver;
    private static IWordParser wordParser;
    private int healthPoints;

    private HangmanGameAnswerEnum gameStateEnum;

    public HangmanGameState(IWordParser wordParser) {
        HangmanGameState.wordParser = wordParser;
        setWord(wordParser);
    }

    public void setWord() {
        setWord(wordParser);
    }

    public void setWord(IWordParser parser) {
        setWord(parser.getNextWord());
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

    public String checkAnswer(String answer) {
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
                return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.WIN, this);
            }
            return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.CORRECT_LETTER, this);
        } else {
            healthPoints--;
            if (healthPoints <= 0) {
                gameIsOver = true;
                return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.LOSE, this);
            }
            return HangmanGameMessages.getMessageForUser(HangmanGameAnswerEnum.WRONG_LETTER, this);
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
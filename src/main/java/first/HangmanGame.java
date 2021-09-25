package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Locale;

public class HangmanGame implements IGame {
    private String word;
    private HashSet<Character> wordHashSet;
    private HashSet<Character> guessedLetters = new HashSet<>();
    private HashSet<Character> usedLetters = new HashSet<>();
    private FileHandler fileHandler;
    private String WIN_TEXT = "Ты выиграл!";
    private String LOSE_TEXT = "Ты проиграл((";
    private String ALREADY_USED_LETTER = "Буква была введена ранее, введите другую!";
    private String ONLY_ONE_LETTER = "Ты должен написать только одну букву!";
    private int unsuccessfulAttemptsCount = 0;

    public HangmanGame() throws FileNotFoundException {
        setWord();
    }

    public void setWord(){
        this.word = fileHandler.getNextWord();
        wordHashSet = getHashSetByWordChars(word);
        guessedLetters = new HashSet<>();
        usedLetters = new HashSet<>();
        unsuccessfulAttemptsCount = 0;
    }
    public void setFile(String fileName) throws FileNotFoundException {
        fileHandler = new FileHandler(new File("hangmanWords.txt"));
    }

    @Override
    public String checkAndGetResult(String answer) {
        if (answer.length() != 1) return ONLY_ONE_LETTER;
        var userChar = answer.toLowerCase().charAt(0);
        if (usedLetters.contains(userChar)) {
            return ALREADY_USED_LETTER;
        }
        usedLetters.add(userChar);
        if (wordHashSet.contains(userChar))
            guessedLetters.add(userChar);
        else unsuccessfulAttemptsCount++;
        if (isWin()) {
            return getWordWithGuessedLetters() + "\n" + WIN_TEXT;
        }
        if (unsuccessfulAttemptsCount > 5) {
            return LOSE_TEXT;
        }
        return getWordWithGuessedLetters() + "\n" + "Осталось жизней: " + (6 - unsuccessfulAttemptsCount);
    }

    public Boolean isWin() {
        return wordHashSet.size() == guessedLetters.size();
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


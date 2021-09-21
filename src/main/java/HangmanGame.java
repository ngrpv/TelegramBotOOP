import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class HangmanGame implements IGame {
    private String word;
    private HashSet<Character> wordHashSet;
    private HashSet<Character> usedLetters = new HashSet<>();
    private String WIN_TEXT = "Ты выиграл!";
    private int attemptsCount = 0;

    public HangmanGame(String word) {
        this.word = word;
        wordHashSet = createHashSetByWordChars(word);
    }

    @Override
    public String checkAnswer(String answer) {
        if (answer.length() != 1) return "Ты должен написать только одну букву!";
        var userChar = answer.toLowerCase(Locale.ROOT).charAt(0);
        if (wordHashSet.contains(userChar))
            usedLetters.add(userChar);
        if (wordHashSet.size() == usedLetters.size()) {
            return getWordWithGuessedLetters() + "\n" + WIN_TEXT;
        }
        if (++attemptsCount > 5) {
            return "Ты проиграл!";
        }
        return getWordWithGuessedLetters() + "\n" + "Отсалось попыток: " + (6 - attemptsCount);
    }

    private String getWordWithGuessedLetters() {
        var resultStr = new StringBuilder();
        for (Character ch : word.toCharArray()) {
            if (usedLetters.contains(ch)) {
                resultStr.append(ch).append(" ");
            } else {
                resultStr.append("* ");
            }
        }
        return resultStr.toString();
    }

    private HashSet<Character> createHashSetByWordChars(String word) {
        var characters = new HashSet<Character>();
        for (Character ch : word.toCharArray()) {
            characters.add(ch);
        }
        return characters;
    }


}


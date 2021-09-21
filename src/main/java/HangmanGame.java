import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashSet;

public class HangmanGame implements IGame {
    private String word;
    private HashSet<Character> wordHashSet;
    private ArrayList<Character> usedLetters = new ArrayList<>();

    public HangmanGame(String word) {
        this.word = word;
        wordHashSet = createHashSetByWordChars(word);
    }

    @Override
    public Pair<Boolean, String> checkAnswer(String answer) {
        if (answer.length() != 1) return new Pair<>(false, "Ты должен написать только одну букву!");
        var userChar = answer.charAt(0);
        usedLetters.add(userChar);
        return new Pair<>(true, getWordWithGuessedLetters());
    }

    private String getWordWithGuessedLetters() {
        var resultStr = new StringBuilder();
        for (Character ch : word.toCharArray()) {
           if(usedLetters.contains(ch)){
               resultStr.append(ch).append(" ");
           }else{
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


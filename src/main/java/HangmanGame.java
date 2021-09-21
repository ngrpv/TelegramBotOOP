import org.javatuples.Pair;

import java.util.List;

public class HangmanGame {
    private String word;
    private List<Character> usedLetters;
    public HangmanGame(String word) {
        this.word = word;
    }

    public Pair<Boolean, String> checkAnswer(String answer){
        if(answer.length() != 0) return new Pair<>(false," ");
        return new  Pair<>(true," ");
    }
}


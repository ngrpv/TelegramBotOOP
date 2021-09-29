import first.FileHandler;
import first.hangman.HangmanGameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class HangmanGameTests {
    @Test
    public void compare_gameWords_with_rode_from_file() {
        HangmanGameState game = getHangmanGame();
        Assertions.assertEquals(getWordFromFile(), game.getWord());
        game.setWord();
        Assertions.assertNotEquals(getWordFromFile(), game.getWord());
    }

    @Test
    public void game_shouldnt_beWon_when_no_letter_entered() {
        Assertions.assertEquals(false, getHangmanGame().isWin());
    }

    @Test
    public void should_reduce_hp_when_incorrect_letter() {
        var game = getHangmanGame();
        var initialHp = game.getHealthPoints();
        game.setWord("aaaaa");
        game.checkAnswer("c");
        Assertions.assertEquals(initialHp - 1, game.getHealthPoints());
    }

    @Test
    public void game_should_be_over_when_hp_run_out() {
        var game = getHangmanGame();
        game.setWord("aaaa");
        var initialHp = game.getHealthPoints();
        for (var i = 0; i < initialHp; i++) {
            game.checkAnswer(String.valueOf(i));
        }
        Assertions.assertEquals(0, game.getHealthPoints());
        Assertions.assertEquals(true, game.isOver());
    }

    private HangmanGameState getHangmanGame() {
        HangmanGameState game = null;
        try {
            game = new HangmanGameState(new FileHandler());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }

    private String getWordFromFile() {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileHandler != null ? fileHandler.getNextWord() : null;
    }
}

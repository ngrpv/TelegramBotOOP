import first.FileHandler;
import first.HangmanGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HangmanGameTests {
    @Test
    public void compare_gameWords_to_getted_from_file() {
        HangmanGame game = getHangmanGame();
        Assertions.assertEquals(getWordFromFile(), game.getWord());
        game.setWord();
        Assertions.assertNotEquals(getWordFromFile(), game.getWord());
    }

    @Test
    public void game_shouldnt_beWinned_when_no_letter_inputed() {
        Assertions.assertEquals(false, getHangmanGame().isWin());
    }

    @Test
    public void should_reduce_hp_when_incorrect_letter(){
        var game = getHangmanGame();
        var initialHp = game.getHealthPoints();
        game.setWord("aaaaa");
        game.checkAnswer("c");
        Assertions.assertEquals(initialHp-1, game.getHealthPoints());
    }

    private HangmanGame getHangmanGame() {
        HangmanGame game = null;
        try {
            game = new HangmanGame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }

    private String getWordFromFile() {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("hangmanWords.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileHandler != null ? fileHandler.getNextWord() : null;
    }
}

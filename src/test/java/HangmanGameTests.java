import first.FileHandler;
import first.hangman.HangmanGameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HangmanGameTests {
    @Test
    public void game_shouldnt_beWon_when_no_letter_entered() {
        Assertions.assertEquals(false, new HangmanGameState().isWin());
    }

    @Test
    public void should_reduce_hp_when_incorrect_letter() {
        var game = new HangmanGameState();
        var initialHp = game.getHealthPoints();
        game.setWord("aaaaa");
        game.checkAnswer("c");
        Assertions.assertEquals(initialHp - 1, game.getHealthPoints());
    }

    @Test
    public void game_should_be_over_when_hp_run_out() {
        var game = new HangmanGameState();
        game.setWord("aaaa");
        var initialHp = game.getHealthPoints();
        for (var i = 0; i < initialHp; i++) {
            game.checkAnswer(String.valueOf(i));
        }
        Assertions.assertEquals(0, game.getHealthPoints());
        Assertions.assertEquals(true, game.isOver());
    }


    private String getWordFromFile() {
        var fileHandler = FileHandler.getParser("hangmanWords.txt");
        return fileHandler != null ? fileHandler.getWord() : null;
    }
}

import first.games.hangman.HangmanGameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HangmanGameTests {
    @Test
    public void isWin_shouldBeFalse_ifNewGameCreated() {
        Assertions.assertEquals(false, new HangmanGameState(0L).isWin());
    }

    @Test
    public void should_reduce_hp_when_incorrect_letter() {
        var game = new HangmanGameState(0L);
        var initialHp = game.getHealthPoints();
        game.setWord("aaaaa");
        game.checkAnswer("c");
        Assertions.assertEquals(initialHp - 1, game.getHealthPoints());
    }

    @Test
    public void game_should_restart_when_hp_run_out() {
        var game = new HangmanGameState(0L);
        game.setWord("aaaa");
        var initialHp = game.getHealthPoints();
        for (var i = 0; i < initialHp; i++) {
            game.checkAnswer(String.valueOf(i));
        }
        Assertions.assertEquals(6, game.getHealthPoints());
        Assertions.assertEquals(false, game.isOver());
    }
}

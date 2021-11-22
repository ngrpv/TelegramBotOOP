import first.user.User;
import first.bot.BotLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class BotLogicTests {

    @Test
    public void user_state_should_True_if_starting_new_game()
    {
        String START_GAME = "/hangman";
        User user = new User(0);
        user.userName = "Test";
        var str = BotLogic.handleMessage(START_GAME, user);
        Assertions.assertTrue(user.isPlaying());
    }

    @Test
    public void guessed_word_should_True_if_game_restarted()
    {
        String RESTART_GAME = "/restart";
        User user = new User(0);
        user.userName = "Test";
        var firstGuessedWord = BotLogic.handleMessage("/cowsAndBulls",user);
        var secondGuessedWord = BotLogic.handleMessage(RESTART_GAME,user);
        Assertions.assertNotEquals(firstGuessedWord, secondGuessedWord);
    }



}

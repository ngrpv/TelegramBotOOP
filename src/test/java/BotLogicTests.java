import first.User;
import first.BotLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class BotLogicTests {

    @Test
    public void state_should_isPlayingTrue_because_starting_game()
    {
        String START_GAME = "/hangman";
        User user = new User(false);
        var str = BotLogic.getMessageForUser(START_GAME, user);
        Assertions.assertTrue(user.isPlaying);
    }

    @Test
    public void output_should_hidden_if_restarting_game_and_isPlaying_should_be_True()
    {
        String RESTART_GAME = "/restart";
        User user = new User(false);
        var str = BotLogic.getMessageForUser(RESTART_GAME, user);
        Assertions.assertFalse(checkUniqueChars(str));
        Assertions.assertTrue(user.isPlaying);
    }
    static boolean checkUniqueChars(String s) {
        final Set<Character> chars = new HashSet<>();
        for (final char c : s.toCharArray()) {
            if (chars.contains(c)) {
                return false;
            }
            chars.add(c);
        }
        return true;
    }



}

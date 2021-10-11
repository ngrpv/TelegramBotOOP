import first.UserState;
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
        UserState userState = new UserState(false);
        var str = BotLogic.getMessageForUser(START_GAME,userState);
        Assertions.assertTrue(userState.isPlaying);
    }

    @Test
    public void output_should_hidden_if_restarting_game_and_isPlaying_should_be_True()
    {
        String RESTART_GAME = "/restart";
        UserState userState = new UserState(false);
        var str = BotLogic.getMessageForUser(RESTART_GAME,userState);
        Assertions.assertFalse(checkUniqueChars(str));
        Assertions.assertTrue(userState.isPlaying);
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

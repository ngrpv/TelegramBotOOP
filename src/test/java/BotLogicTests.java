import first.UserState;
import first.BotLogic;
import org.glassfish.grizzly.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import first.HangmanGame;
import first.BotLogic;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BotLogicTests {

    @Test
    public void state_should_isPlaingTrue_because_starting_game()
    {
        String START_GAME = "/hangman";
        UserState userState = new UserState(false);
        var str = BotLogic.getMessageForUser(START_GAME,userState);
        Assertions.assertEquals(userState.isPlaying,true);
    }

    @Test
    public void output_should_hidden_if_restarting_game_and_isPlaying_should_be_True()
    {
        String RESTART_GAME = "/restart";
        UserState userState = new UserState(false);
        var str = BotLogic.getMessageForUser(RESTART_GAME,userState);
        Assertions.assertEquals(false,checkUniqueChars(str));
        Assertions.assertEquals(true,userState.isPlaying);
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

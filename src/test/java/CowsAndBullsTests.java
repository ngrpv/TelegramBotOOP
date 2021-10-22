import first.cowsAndBulls.CowsAndBullsEnum;
import first.cowsAndBulls.CowsAndBullsState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CowsAndBullsTests {

    @Test
    public void word_should_be_correct_in_length() {
        var game = new CowsAndBullsState();
        game.setWord("папаша");
        var message = game.checkAnswer("папа");
        var WORD_EQUAL_LENGTH = "Введите число той же длины, что и загадано!";
        Assertions.assertSame(message, WORD_EQUAL_LENGTH);
    }
    @Test
    public void check_correct_count_cows()
    {
        var game  = new CowsAndBullsState();
        game.setWord("жигало");
        var bulls = game.checkAnswer("гигант").toCharArray();
        var cows = game.checkAnswer("гигант").toCharArray();
        Assertions.assertEquals(bulls[5],'3');
        Assertions.assertEquals(cows[14],'0');
    }

    public int[] getCowsAndBulls(String userWord,String word) {
        var game = new CowsAndBullsState();
        game.setWord(word);
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        return new int[]{bulls,cows};
    }

    @Test
    public void check_correct_count_cowsAndBulls() {
        var bulls = getCowsAndBulls("гигант","жигало")[0];
        var cows = getCowsAndBulls("гигант","жигало")[1];
        Assertions.assertEquals(bulls, 3);
        Assertions.assertEquals(cows, 0);
    }

    @Test
    public void check_correct_count_cowsAndBulls2() {
        var bulls = getCowsAndBulls("гигант","город")[0];
        var cows = getCowsAndBulls("гигант","город")[1];
        Assertions.assertEquals(bulls, 1);
        Assertions.assertEquals(cows, 0);
    }

    @Test
    public void check_correct_count_cowsAndBulls3() {
        var bulls = getCowsAndBulls("катп","анас")[0];
        var cows = getCowsAndBulls("катп","анас")[1];
        Assertions.assertEquals(bulls, 0);
        Assertions.assertEquals(cows, 1);
    }

    @Test
    public void check_correct_count_cowsAndBulls4() {
        var bulls = getCowsAndBulls("палка","лейка")[0];
        var cows = getCowsAndBulls("палка","лейка")[1];
        Assertions.assertEquals(bulls, 2);
        Assertions.assertEquals(cows, 1);
    }

    @Test
    public void check_correct_count_cowsAndBulls5() {
        var bulls = getCowsAndBulls("окурок","крутой")[0];
        var cows = getCowsAndBulls("окурок","крутой")[1];
        Assertions.assertEquals(bulls, 2);
        Assertions.assertEquals(cows, 2);
    }

    @Test
    public void check_correct_count_cowsAndBulls6() {
        var bulls = getCowsAndBulls("лепс","спел")[0];
        var cows = getCowsAndBulls("лепс","спел")[1];
        Assertions.assertEquals(bulls, 0);
        Assertions.assertEquals(cows, 4);
    }
@Test
    public void check_correct_string_of_empty_char() {
        var bulls = getCowsAndBulls("    ","спел")[0];
        var cows = getCowsAndBulls("    ","спел")[1];
        Assertions.assertEquals(bulls, 0);
        Assertions.assertEquals(cows, 0);
    }



}

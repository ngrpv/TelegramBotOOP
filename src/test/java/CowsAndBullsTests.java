import first.cowsAndBulls.CowsAndBullsState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
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

    public int[] getCowsAndBulls(String userWord, String word) {
        var game = new CowsAndBullsState();
        game.setWord(word);
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        return new int[]{bulls, cows};
    }

    @Test
    public void check_correct_count_cowsAndBulls() {
        Assertions.assertEquals(3,getCowsAndBulls("гигант","жигало")[0]);
        Assertions.assertEquals(0,getCowsAndBulls("гигант","жигало")[1]);
    }

    @Test
    public void check_correct_count_cowsAndBulls2() {
        Assertions.assertEquals(1,getCowsAndBulls("гигант","город")[0]);
        Assertions.assertEquals(0,getCowsAndBulls("гигант","город")[1]);
    }

    @Test
    public void check_correct_count_cowsAndBulls3() {
        Assertions.assertEquals(0,getCowsAndBulls("катп","анас")[0]);
        Assertions.assertEquals(1,getCowsAndBulls("катп","анас")[1]);
    }

    @Test
    public void check_correct_count_cowsAndBulls4() {
        Assertions.assertEquals(2,getCowsAndBulls("палка","лейка")[0]);
        Assertions.assertEquals(1,getCowsAndBulls("палка","лейка")[1]);
    }

    @Test
    public void check_correct_count_cowsAndBulls5() {
        Assertions.assertEquals(2,getCowsAndBulls("окурок","крутой")[0]);
        Assertions.assertEquals(2,getCowsAndBulls("окурок","крутой")[1]);
    }

    @Test
    public void check_correct_count_cowsAndBulls6() {
        Assertions.assertEquals(0,getCowsAndBulls("лепс","спел")[0]);
        Assertions.assertEquals(4,getCowsAndBulls("лепс","спел")[1]);
    }

    @Test
    public void check_correct_string_of_empty_char() {
        Assertions.assertEquals(0,getCowsAndBulls("    ","спел")[0]);
        Assertions.assertEquals(0,getCowsAndBulls("    ","спел")[1]);
    }

    @Test
    public void check_correct_count_cowsAndBulls7() {
        Assertions.assertEquals(0,getCowsAndBulls("5522","2255")[0]);
        Assertions.assertEquals(4,getCowsAndBulls("5522","2255")[1]);
    }
    @Test
    public void check_correct_count_cowsAndBulls8() {
        Assertions.assertEquals(2,getCowsAndBulls("2222","2255")[0]);
        Assertions.assertEquals(0,getCowsAndBulls("2222","2255")[1]);
    }
    @Test
    public void check_correct_count_cowsAndBulls9() {
        Assertions.assertEquals(1,getCowsAndBulls("0003","1323")[0]);
        Assertions.assertEquals(0,getCowsAndBulls("0003","1323")[1]);
    }
    @Test
    public void check_correct_count_cowsAndBulls10() {
        Assertions.assertEquals(0,getCowsAndBulls("00002","1112")[0]);
        Assertions.assertEquals(1,getCowsAndBulls("00002","1112")[1]);
    }
    @Test
    public void check_correct_count_cowsAndBulls11() {
        Assertions.assertEquals(3,getCowsAndBulls("0209","0009")[0]);
        Assertions.assertEquals(0,getCowsAndBulls("0209","0009")[1]);
    }


}

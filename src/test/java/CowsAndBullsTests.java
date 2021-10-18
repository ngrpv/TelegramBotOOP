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
        Assertions.assertEquals(message.compareTo(WORD_EQUAL_LENGTH), 0);
    }

    @Test
    public void check_correct_count_cowsAndBulls() {
        var game = new CowsAndBullsState();
        game.setWord("жигало");
        var userWord = "гигант";
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        Assertions.assertEquals(bulls, 3);
        Assertions.assertEquals(cows, 0);
    }

    @Test
    public void check_correct_count_cowsAndBulls2() {
        var game = new CowsAndBullsState();
        game.setWord("город");
        var userWord = "гигант";
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        Assertions.assertEquals(bulls, 1);
        Assertions.assertEquals(cows, 0);
    }

    @Test
    public void check_correct_count_cowsAndBulls3() {
        var game = new CowsAndBullsState();
        game.setWord("анас");
        var userWord = "катп";
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        Assertions.assertEquals(bulls, 0);
        Assertions.assertEquals(cows, 1);
    }

    @Test
    public void check_correct_count_cowsAndBulls4() {
        var game = new CowsAndBullsState();
        game.setWord("лейка");
        var userWord = "палка";
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        Assertions.assertEquals(bulls, 2);
        Assertions.assertEquals(cows, 1);
    }

    @Test
    public void check_correct_count_cowsAndBulls5() {
        var game = new CowsAndBullsState();
        game.setWord("крутой");
        var userWord = "окурок";
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        Assertions.assertEquals(bulls, 2);
        Assertions.assertEquals(cows, 2);
    }

    @Test
    public void check_correct_count_cowsAndBulls6() {
        var game = new CowsAndBullsState();
        game.setWord("спел");
        var userWord = "лепс";
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        Assertions.assertEquals(bulls, 0);
        Assertions.assertEquals(cows, 4);
    }
@Test
    public void check_correct_string_of_empty_char() {
        var game = new CowsAndBullsState();
        game.setWord("спел");
        var userWord = "    ";
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        Assertions.assertEquals(bulls, 0);
        Assertions.assertEquals(cows, 0);
    }



}

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

    public int[] getCowsAndBulls(String userWord, String word) {
        var game = new CowsAndBullsState();
        game.setWord(word);
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        return new int[]{bulls, cows};
    }

    @Test
    public void check_correct_count_cowsAndBulls() {
        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(new int[]{3, 0}, getCowsAndBulls("гигант", "жигало")),
                () -> Assertions.assertArrayEquals(new int[]{1, 0}, getCowsAndBulls("гигант", "город")),
                () -> Assertions.assertArrayEquals(new int[]{0, 1}, getCowsAndBulls("катп", "анас")),
                () -> Assertions.assertArrayEquals(new int[]{2, 1}, getCowsAndBulls("палка", "лейка")),
                () -> Assertions.assertArrayEquals(new int[]{2, 2}, getCowsAndBulls("окурок", "крутой")),
                () -> Assertions.assertArrayEquals(new int[]{0, 4}, getCowsAndBulls("лепс", "спел")),
                () -> Assertions.assertArrayEquals(new int[]{0, 0}, getCowsAndBulls("    ", "спел")),
                () -> Assertions.assertArrayEquals(new int[]{0, 4}, getCowsAndBulls("5522", "2255")),
                () -> Assertions.assertArrayEquals(new int[]{2, 0}, getCowsAndBulls("2222", "2255")),
                () -> Assertions.assertArrayEquals(new int[]{1, 0}, getCowsAndBulls("0003", "1323")),
                () -> Assertions.assertArrayEquals(new int[]{0, 1}, getCowsAndBulls("00002", "1112")),
                () -> Assertions.assertArrayEquals(new int[]{3, 0}, getCowsAndBulls("0209", "0009")));
    }

}

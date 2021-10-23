import first.cowsAndBulls.CowsAndBullsState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CowsAndBullsTests {

    CowsAndBullsState game;


    @Test
    public void word_should_be_correct_in_length() {
        var game = new CowsAndBullsState();
        game.setWord("папаша");
        var message = game.checkAnswer("папа");
        var WORD_EQUAL_LENGTH = "Введите число той же длины, что и загадано!";
        Assertions.assertSame(message, WORD_EQUAL_LENGTH);
    }

    private int[] getCowsAndBulls(String userWord, String word) {
        var game = new CowsAndBullsState();
        game.setWord(word);
        var bulls = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[5]);
        var cows = Character.getNumericValue(game.checkAnswer(userWord).toCharArray()[14]);
        return new int[]{bulls, cows};
    }

    private void check_counting_cows_and_bulls(String userWord, String guessed, int expectedBulls, int expectedCows) {
        //Arrange
        game.setWord(guessed);
        //Act
        var cowsAndBulls = game.getCowsAndBulls(userWord, guessed);
        //Assert
        Assertions.assertEquals(expectedCows, cowsAndBulls[0]);
        Assertions.assertEquals(expectedBulls, cowsAndBulls[1]);
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

    @BeforeEach
    public void setUp() {
        game = new CowsAndBullsState();
    }

    @Test
    public void check_common_cases() {
        check_counting_cows_and_bulls("гигант", "жигало", 3, 0);
        check_counting_cows_and_bulls("гигант", "город", 1, 0);
    }

    @Test
    public void check_if_empty_user_answer(){
        check_counting_cows_and_bulls("    ", "спел", 0,0);
    }

    //...
}

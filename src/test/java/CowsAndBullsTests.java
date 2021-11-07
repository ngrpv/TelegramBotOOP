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

    private int[] getCowsAndBulls(String userWord, String word) { //todo Unused
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

    @BeforeEach
    public void setUp() {
        game = new CowsAndBullsState();
    }

    @Test
    public void check_common_cases() {
        check_counting_cows_and_bulls("гигант", "жигало", 3, 0);
        check_counting_cows_and_bulls("гигант", "город", 1, 0);
        check_counting_cows_and_bulls("палка", "лейка", 2, 1);
        check_counting_cows_and_bulls("окурок", "крутой", 2, 2);
        check_counting_cows_and_bulls("1242","2322",1,1);
        check_counting_cows_and_bulls("0003","1323",1,0);
    }

    @Test
    public void check_if_empty_user_answer(){
        check_counting_cows_and_bulls("    ", "спел", 0,0);
    }

    @Test
    public void check_if_user_length_longer_word() {
        check_counting_cows_and_bulls("деревья","дерево",5,0);
    }

    @Test
    public void check_if_user_message_is_palindrome() {
        check_counting_cows_and_bulls("лепс","спел",0,4);
        check_counting_cows_and_bulls("2255","5522",0,4);
    }

    @Test
    public void check_if_one_letter_is_missing() {
        check_counting_cows_and_bulls("ка та","карта",4,0);
    }

    @Test
    public void check_wordWithDigit_() {
        check_counting_cows_and_bulls("сл0н","слон",3,0);
    }

    @Test
    public void check_if_word_of_same_letters(){
        check_counting_cows_and_bulls("ааааа","буква",1,0);
    }


}

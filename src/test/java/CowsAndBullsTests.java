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
        var WORD_EQUAL_LENGTH = "Введите слово той же длины, что и загадано!";
        Assertions.assertSame(message, WORD_EQUAL_LENGTH);
    }
    @Test
    public void check_correct_count_cows()
    {
        var game  = new CowsAndBullsState();
        game.setWord("жигало");
        var bulls = game.checkAnswer("гигант").toCharArray();
        var cows = game.checkAnswer("гигант").toCharArray();
        Assertions.assertEquals(bulls[5]-'0',3);
        Assertions.assertEquals(cows[14]-'0',0);
    }
    public void check_correct_count_cows2()
    {
        var game  = new CowsAndBullsState();
        game.setWord("город");
        var bulls = game.checkAnswer("онгрв").toCharArray();
        var cows = game.checkAnswer("гигант").toCharArray();
        Assertions.assertEquals(bulls[5]-'0',0);
        Assertions.assertEquals(cows[14]-'0',1);
    }
    public void check_correct_count_cows3()
    {
        var game  = new CowsAndBullsState();
        game.setWord("анас");
        var bulls = game.checkAnswer("катп").toCharArray();
        var cows = game.checkAnswer("катп").toCharArray();
        Assertions.assertEquals(bulls[5]-'0',0);
        Assertions.assertEquals(cows[14]-'0',1);
    }
    public void check_correct_count_cows4()
    {
        var game  = new CowsAndBullsState();
        game.setWord("лейка");
        var bulls = game.checkAnswer("палка").toCharArray();
        var cows = game.checkAnswer("палка").toCharArray();
        Assertions.assertEquals(bulls[5]-'0',2);
        Assertions.assertEquals(cows[14]-'0',1);
    }
    public void check_correct_count_cows5()
    {
        var game  = new CowsAndBullsState();
        game.setWord("крутой");
        var bulls = game.checkAnswer("окурок").toCharArray();
        var cows = game.checkAnswer("окурок").toCharArray();
        Assertions.assertEquals(bulls[5]-'0',2);
        Assertions.assertEquals(cows[14]-'0',2);
    }
    public void check_correct_count_cows6()
    {
        var game  = new CowsAndBullsState();
        game.setWord("спел");
        var bulls = game.checkAnswer("лепс").toCharArray();
        var cows = game.checkAnswer("лепс").toCharArray();
        Assertions.assertEquals(bulls[5]-'0',0);
        Assertions.assertEquals(cows[14]-'0',4);
    }

}

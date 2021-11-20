import first.games.cowsAndBulls.CowsAndBullsWordParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CowsAndBullsWordParserTests {
    @Test
    public void check_if_getWord_returns_word_or_number() {
        var generator = new CowsAndBullsWordParser();
        for (int i = 0; i < 10; i++) {
            var generated = generator.getWord();
            Assertions.assertTrue(isWord(generated) || isInt(generated));
        }
    }

    private Boolean isWord(String word) {
        for (char ch :
                word.toCharArray()) {
            if (!Character.isLetter(ch))
                return false;
        }
        return true;
    }

    private Boolean isInt(String word) {
        try {
            int num = Integer.parseInt(word);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

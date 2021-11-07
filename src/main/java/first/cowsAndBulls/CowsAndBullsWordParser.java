package first.cowsAndBulls;

import first.FileHandler;
import first.IWordParser;

import java.util.Random;

public class CowsAndBullsWordParser implements IWordParser {
    // todo: две реализации и подставляйте нужную
    private static final Random random = new Random();
    private static final IWordParser wordParser = new FileHandler("hangmanWords.txt");

    @Override
    public String getWord() {
//        var word = wordParser.getWord();
//        while (word.length() > 6 || word.length() < 4) {
//           word = wordParser.getWord();
//        }
//        return word;
        return String.valueOf(getRandomInt());
    }

    private int getRandomInt() {
        return random.nextInt(9999);
    }
}

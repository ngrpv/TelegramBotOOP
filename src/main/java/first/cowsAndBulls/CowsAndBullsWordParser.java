package first.cowsAndBulls;

import first.IWordParser;

import java.util.Random;

public class CowsAndBullsWordParser implements IWordParser {
    private static final Random random = new Random();
    @Override
    public String getWord() {
        return String.valueOf(getRandomInt());
    }

    private int getRandomInt(){
        return random.nextInt(9999);
    }
}

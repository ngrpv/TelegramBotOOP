package first;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

public class FileHandler implements IWordParser {
    private static String[] words;
    private static final Random random = new Random();
    public FileHandler(String fileName) {
        words = getWordsFromFile(fileName);
    }

    private String[] getWordsFromFile(String fileName) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(new File(fileName).toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert lines != null;
        return lines.toArray(new String[0]);
    }

    public String getWord() {
        return words[random.nextInt(words.length-1)];
    }
}

package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

public class FileHandler implements IWordParser {
    private static String[] words;

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
        return lines.toArray(new String[lines.size()]);
    }

    public String getWord() {
        Random rand = new Random();
        return words[rand.nextInt(words.length-1)];
    }
}

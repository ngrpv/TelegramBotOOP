package first;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class FileHandler implements IWordParser {
    private String fileName;

    public static FileHandler getParser(String fileName){
        try {
            return new FileHandler(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FileHandler(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    public String getWord() {
        Random rand = new Random();
        String result = "О";
        int n = 0;
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            assert fileScanner != null;
            if (!fileScanner.hasNext()) break;
            n++;
            String word = fileScanner.nextLine();
            if (rand.nextInt(n) == 0)
                result = word;
        }

        return result;
    }
}

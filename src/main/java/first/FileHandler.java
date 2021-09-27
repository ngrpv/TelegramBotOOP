package first;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileHandler {
    Scanner fileScanner;

    public  FileHandler(String fileName) throws FileNotFoundException {
        fileScanner = new Scanner(new BufferedReader(new FileReader("hangmanWords.txt")));
    }
    
    public String getNextWord(){
        if(fileScanner.hasNext()){
            return fileScanner.nextLine();
        }

        return null;
    }
}

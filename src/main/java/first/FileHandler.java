package first;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileHandler implements IWordParser{
    Scanner fileScanner;

    public  FileHandler() throws FileNotFoundException {
        fileScanner = new Scanner(new BufferedReader(new FileReader("hangmanWords.txt")));
    }
    
    public String getNextWord(){
        if(fileScanner.hasNext()){
            return fileScanner.nextLine();
        }

        return null;
    }
}

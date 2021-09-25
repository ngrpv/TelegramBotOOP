package first;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    Scanner fileScanner;

    public FileHandler(BufferedReader file) {
        fileScanner = new Scanner(file);
    }
    
    public String getNextWord(){
        if(fileScanner.hasNext()){
            return fileScanner.nextLine();
        }

        return "worker";
    }
}

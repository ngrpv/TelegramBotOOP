import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    File file;
    Scanner fileScanner;

    public FileHandler(File file) throws FileNotFoundException {
        this.file = file;
        fileScanner = new Scanner(file);
    }
    
    public String getNextWord(){
        if(fileScanner.hasNext()){
            return fileScanner.nextLine();
        }
        return "";
    }
}

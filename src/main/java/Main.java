import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var game = new HangmanGame("барабан");
        var scanner = new Scanner(System.in);
        while(true){
            var userMessage = scanner.nextLine();
            if(userMessage.equals("exit")) break;
            System.out.println(game.checkAnswer(userMessage));
        }
    }
}

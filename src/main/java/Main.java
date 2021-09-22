import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var game = new HangmanGame("барабан");
        var scanner = new Scanner(System.in);
        System.out.println(game.getHiddenWord());
        while(scanner.hasNext()){
            var userMessage = scanner.nextLine();
            if(userMessage.length() == 0) continue;
            if(userMessage.equals("exit")) break;
            System.out.println(game.checkAnswer(userMessage));
            if(game.isWin()){
                game = new HangmanGame("sadsadas");
            }
        }
    }
}

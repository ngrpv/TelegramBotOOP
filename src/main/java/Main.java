import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        launchBot();
    }

    /*private static void launchInConsole() {
        HangmanGame game = null;
        try {
            game = new HangmanGame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var scanner = new Scanner(System.in);
        System.out.println(game.getHiddenWord());
        while (scanner.hasNext()) {
            var userMessage = scanner.nextLine();
            if (userMessage.length() == 0) continue;
            if (userMessage.equals("exit")) break;
            System.out.println(game.checkAndGetResult(userMessage));
            if (game.isWin()) {
                game.setWord();
            }
        }
    }*/

    private static void launchBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TGBot("NRGNbot", "2043235759:AAF_cqZzorfAKgQpMCNKGHM4trXnMoaBG1k"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
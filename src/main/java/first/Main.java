package first;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;




public class Main {
    public static void main(String[] args) {
        ConsoleBot.launch();
        //launchBot();
    }

    private static void launchBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TGBot("NRGNbot"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
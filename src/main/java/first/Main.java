package first;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static BotHandler start  = new BotHandler();
    public static void main(String[] args) {
        start.launchInConsole();
    }



    private static void launchBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TGBot("NRGNbot", "2043235759:AAF_cqZzorfAKgQpMCNKGHM4trXnMoaBG1k"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
package first;

import first.database.Database;
import first.user.User;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;




public class Main {
    public static void main(String[] args) {
        //ConsoleBot.launch();
        //launchBot();
        Database db = Database.tryGetDatabase();
        assert db != null;
        //System.out.println(db.getUser(23));
        db.updateOrAdd(new User(23));
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
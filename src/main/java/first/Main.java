package first;

import first.database.HibernateUtil;
import first.user.User;
import org.hibernate.Session;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.persistence.FlushModeType;


public class Main {
    public static void main(String[] args) {
        //ConsoleBot.launch();
        //launchBot();
        var session = HibernateUtil.getSessionFactory().openSession();
        var t = session.beginTransaction();
        var user = new User(233);
        user.userName = "ddasdas";
        session.save(user);
        session.persist(user);
        System.out.println(session.get(User.class, 233L).userName);
        session.flush();
        t.commit();
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
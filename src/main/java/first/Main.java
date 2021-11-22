package first;

import first.bot.ConsoleBot;
import first.bot.TGBot;
import first.database.HibernateDatabase;
import first.repository.CowsAndBullsRepository;
import first.user.User;

import javax.persistence.Id;


public class Main {
    public static void main(String[] args) {
        //ConsoleBot.launch();
        TGBot.launch();


    }
}
package first.database.repository;

import first.games.IGame;
import first.games.hangman.HangmanGameState;
import org.hibernate.SessionFactory;

public class HangmanRepository extends Repository<HangmanGameState> {
    public HangmanRepository(SessionFactory sessionFactory) {
        super(HangmanGameState.class, sessionFactory);
    }
}

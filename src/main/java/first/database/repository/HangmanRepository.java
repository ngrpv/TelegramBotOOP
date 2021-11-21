package first.database.repository;

import first.database.IDatabase;
import first.games.IGame;
import first.games.hangman.HangmanGameState;
import org.hibernate.SessionFactory;

public class HangmanRepository extends Repository<HangmanGameState> {
    public HangmanRepository(IDatabase database) {
        super(HangmanGameState.class, database);
    }
}

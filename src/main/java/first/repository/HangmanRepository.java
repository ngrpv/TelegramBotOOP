package first.repository;

import first.database.IDatabase;
import first.games.hangman.HangmanGameState;

public class HangmanRepository extends Repository<HangmanGameState> {
    public HangmanRepository(IDatabase database) {
        super(HangmanGameState.class, database);
    }
}

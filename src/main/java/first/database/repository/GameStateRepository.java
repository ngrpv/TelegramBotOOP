package first.database.repository;

import first.database.IDatabase;
import first.games.GameType;
import first.games.IGame;
import org.hibernate.SessionFactory;

public class GameStateRepository extends Repository<IGame> {
    private static HangmanRepository hangmanRepository;
    private static CowsAndBullsRepository cowsAndBullsRepository;

    public GameStateRepository(IDatabase database) {
        super(IGame.class, database);
        hangmanRepository = new HangmanRepository(database);
        cowsAndBullsRepository = new CowsAndBullsRepository(database);
    }

    public IGame get(long id, GameType gameType) {
        var repository = getGameRepository(gameType);
        return repository.get(id);
    }


    private Repository<? extends IGame> getGameRepository(GameType gameType) {
        if (gameType == null) return hangmanRepository;
        return switch (gameType) {
            case Hangman -> hangmanRepository;
            case CowsAndBulls -> cowsAndBullsRepository;
        };
    }
}

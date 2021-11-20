package first.database.repository;

import first.games.GameType;
import first.games.IGame;
import org.hibernate.SessionFactory;

public class GameStateRepository extends Repository<IGame> {
    private static HangmanRepository hangmanRepository;
    private static CowsAndBullsRepository cowsAndBullsRepository;

    public GameStateRepository(SessionFactory sessionFactory) {
        super(IGame.class, sessionFactory);
        hangmanRepository = new HangmanRepository(sessionFactory);
        cowsAndBullsRepository = new CowsAndBullsRepository(sessionFactory);
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

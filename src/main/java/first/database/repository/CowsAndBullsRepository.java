package first.database.repository;

import first.games.IGame;
import first.games.cowsAndBulls.CowsAndBullsState;
import org.hibernate.SessionFactory;

public class CowsAndBullsRepository extends Repository<CowsAndBullsState> {
    public CowsAndBullsRepository(SessionFactory sessionFactory) {
        super(CowsAndBullsState.class, sessionFactory);
    }
}

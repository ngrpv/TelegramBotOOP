package first.database.repository;

import first.database.IDatabase;
import first.games.cowsAndBulls.CowsAndBullsState;

public class CowsAndBullsRepository extends Repository<CowsAndBullsState> {
    public CowsAndBullsRepository(IDatabase database) {
        super(CowsAndBullsState.class, database);
    }
}

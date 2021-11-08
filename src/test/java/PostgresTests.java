import first.database.PostgresDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PostgresTests {
    @Test
    public void getUser_ShouldReturnUser(){
        var db = PostgresDatabase.tryGetDatabase();
        assert db != null;
        var user = db.getUser(0);
        Assertions.assertEquals(user.score, 50);
        Assertions.assertEquals(user.userName, "Nazar");
    }
}

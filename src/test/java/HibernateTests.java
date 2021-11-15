import first.database.HibernateDatabase;
import first.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HibernateTests {
    @Test
    public void getUser_ShouldReturnUser(){
        var db = new HibernateDatabase();
        var user = new User(0L);
        var username = "test";
        user.userName = username;
        db.addUser(user);

        var actual = db.getUser(0L);

        Assertions.assertEquals(actual.score, 0);
        Assertions.assertEquals(actual.userName, username);
    }
}

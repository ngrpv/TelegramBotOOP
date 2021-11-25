import first.database.HibernateDatabase;
import first.database.JsonDatabase;
import first.repository.UserRepository;
import first.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseTests {
    @Test
    public void getUser_ShouldReturnUser() {
        var db = new UserRepository(new JsonDatabase("jsonDataB"));
        var user = new User(7L);
        var username = "test";
        user.userName = username;
        db.save(user);

        var actual = db.get(7L);

        Assertions.assertEquals(actual.score, 0);
        Assertions.assertEquals(actual.userName, username);
    }

    @Test
    public void hibernate_getUser_ShouldReturnUser() {
        var db = new UserRepository(new HibernateDatabase());
        var user = new User(11L);
        var username = "test";
        user.userName = username;
        db.saveOrUpdate(user);

        var actual = db.get(11L);

        Assertions.assertEquals(actual.score, 0);
        Assertions.assertEquals(actual.userName, username);
    }
}

import first.HibernateUtil;
import first.database.repository.UserRepository;
import first.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HibernateTests {
    @Test
    public void getUser_ShouldReturnUser(){
        var db = new UserRepository(HibernateUtil.getSessionFactory());
        var user = new User(2L);
        var username = "test";
        user.userName = username;
        db.save(user);

        var actual = db.get(2L);

        Assertions.assertEquals(actual.score, 0);
        Assertions.assertEquals(actual.userName, username);
    }
}

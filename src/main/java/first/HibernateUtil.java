package first;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        URI dbUri = null;
        try {
            dbUri = new URI(System.getenv("DATABASE_URL"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert dbUri != null;
        String connection = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.url", connection)
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password) ;
        sessionFactory = configuration.configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
package first.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.url", connection)
                .setProperty("hibernate.connection.username", System.getenv("DATABASE_USERNAME"))
                .setProperty("hibernate.connection.password", System.getenv("DATABASE_PASSWORD"));
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
        /*sessionFactory = configuration
                .buildSessionFactory(new ServiceRegistryBuilder()
                        .buildServiceRegistry());
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();*/
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
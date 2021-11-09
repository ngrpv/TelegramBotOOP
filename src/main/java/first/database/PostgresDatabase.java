package first.database;


import first.user.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

// todo Репозиторий UserRepository
// ORM hibernate

public class PostgresDatabase implements IDatabase {
    Connection connection;

    public PostgresDatabase() throws SQLException, URISyntaxException {
        connection = getConnection();
    }

    public static PostgresDatabase tryGetDatabase() {
        try {
            return new PostgresDatabase();
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        var query = "INSERT INTO users(id, score, username) VALUES(?::int8, ?::int, ?);";
        trySend(query, String.valueOf(user.getId()), String.valueOf(user.score), user.userName);
    }

    @Override
    public User getUser(long id) {
        var query = "SELECT score, username FROM users WHERE id=?::int8";
        var result = trySend(query, String.valueOf(id));
        assert result != null;
        try {
            if (result.next()) {
                var score = Integer.parseInt(result.getString(1));
                var username = result.getString(2);
                return new User(id).withScore(score).withUserName(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrAdd(User user) {
        var query = "INSERT INTO users (id, score, username) " +
                "VALUES(?::int8, ?::int, ?) " +
                "ON CONFLICT (id) " +
                "    DO\n" +
                "        UPDATE SET score = ?::int, username=?";
        trySend(query, String.valueOf(user.getId()), String.valueOf(user.score), user.userName, String.valueOf(user.score), user.userName);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        var query = "SELECT id, username, score FROM users;";
        var result = trySend(query);
        var users = new ArrayList<User>();
        assert result != null;
        try {
            while (result.next()) {
                var id = Integer.parseInt(result.getString(1));
                var username = result.getString(2);
                var score = Integer.parseInt(result.getString(3));
                users.add(new User(id).withScore(score).withUserName(username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private ResultSet trySend(String query, String... args) {
        ResultSet res = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (var i = 0; i < args.length; i++) {
                preparedStatement.setString(i + 1, args[i]);
            }
            res = preparedStatement.executeQuery();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(res);
        }
        return null;
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}

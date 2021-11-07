package first.database;


import first.user.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;


public class Database implements IDatabase {
    Connection connection;

    public Database() throws SQLException, URISyntaxException {

        connection = getConnection();
    }

    public static Database tryGetDatabase() {
        try {
            return new Database();
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        var query = "INSERT INTO users(id, score) VALUES(?::int8, ?::int);";
        trySend(query, String.valueOf(user.getId()), String.valueOf(user.score));
    }

    @Override
    public User getUser(long id) {
        var query = "SELECT score, id FROM users WHERE id=?::int8";
        var result = trySend(query, String.valueOf(id));
        assert result != null;
        try {
            var score = Integer.parseInt(result.getString("score"));
            return new User(id).withScore(score);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrAdd(User user) {
        var query = "INSERT INTO users (id, score) " +
                "VALUES(?::int8, ?::int) " +
                "ON CONFLICT (id) " +
                "    DO\n" +
                "        UPDATE SET score = ?::int;";
        trySend(query, String.valueOf(user.getId()), String.valueOf(user.score), String.valueOf(user.score));
    }

    private ResultSet trySend(String query, String... args) {
        ResultSet res = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (var i = 0; i < args.length; i++) {
                preparedStatement.setString(i + 1, args[i]);
            }
            res = preparedStatement.executeQuery();
            System.out.println(res);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(res);
        }
        return res;
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}

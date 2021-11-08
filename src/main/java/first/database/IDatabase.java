package first.database;

import first.user.User;

import java.util.ArrayList;

public interface IDatabase {
    void addUser(User user);
    User getUser(long id);
    void updateOrAdd(User user);
    ArrayList<User> getAllUsers();
}

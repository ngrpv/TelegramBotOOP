package first.database;

import first.user.User;

public interface IDatabase {
    void addUser(User user);
    String getUser(long id);
    void updateOrAdd(User user);
}

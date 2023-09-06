package by.tms.calculator.services;

import by.tms.calculator.interfaces.UserStorage;
import by.tms.calculator.storage.userStorage.JdbcUserStorage;
import by.tms.calculator.models.User;

import java.util.Optional;
import java.util.UUID;

public class UserService {

    private final UserStorage userStorage = new JdbcUserStorage();

    public void create(User user) {
        userStorage.add(user);
    }

    public User getById(UUID id) {
        return userStorage.get(id);
    }

    public Optional<User> logIn(String username, String password) {
        return userStorage.get(username, password);
    }

    public boolean updateUsername(User user, String username) {
        user.setUsername(username);

        return userStorage.update(user);
    }

    public boolean updatePassword(User user, String password) {
        user.setPassword(password);

        return userStorage.update(user);
    }
}

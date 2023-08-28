package by.tms.calculator.services;

import by.tms.calculator.interfaces.UserStorage;
import by.tms.calculator.storage.userStorage.JdbcUserStorage;
import by.tms.calculator.models.User;

import java.util.Optional;
import java.util.UUID;

public class UserService {

    private final UserStorage userStorage = new JdbcUserStorage();

    public void create(String username, String password) {
        User newUser = new User(username, password);

        userStorage.add(newUser);
    }

    public User getById(UUID id) {
        return userStorage.get(id);
    }

    public Optional<User> logIn(String username, String password) {
        return userStorage.get(username, password);
    }
}

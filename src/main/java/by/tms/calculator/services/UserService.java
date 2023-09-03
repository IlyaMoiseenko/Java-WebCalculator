package by.tms.calculator.services;

import by.tms.calculator.enums.Role;
import by.tms.calculator.interfaces.UserStorage;
import by.tms.calculator.interfaces.Validation;
import by.tms.calculator.storage.userStorage.JdbcUserStorage;
import by.tms.calculator.models.User;
import by.tms.calculator.utils.Validator;

import java.util.Optional;
import java.util.UUID;

public class UserService {

    private final UserStorage userStorage = new JdbcUserStorage();
    private final Validation validation = new Validator();

    public void create(String username, String password) {
        User newUser = new User(username, password, Role.USER);

        if (validation.validate(newUser))
            userStorage.add(newUser);
    }

    public User getById(UUID id) {
        return userStorage.get(id);
    }

    public Optional<User> logIn(String username, String password) {
        return userStorage.get(username, password);
    }

    public boolean updateUsername(User user, String username) {
        user.setUsername(username);

        if (validation.validate(user)) {
            userStorage.update(user);

            return true;
        } else
            return false;
    }

    public boolean updatePassword(User user, String password) {
        user.setPassword(password);

        if (validation.validate(user)) {
            userStorage.update(user);

            return true;
        } else
            return false;
    }
}

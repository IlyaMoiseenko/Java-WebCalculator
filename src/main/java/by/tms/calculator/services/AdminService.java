package by.tms.calculator.services;

import by.tms.calculator.enums.Role;
import by.tms.calculator.interfaces.UserStorage;
import by.tms.calculator.models.User;
import by.tms.calculator.storage.userStorage.JdbcUserStorage;

import java.util.List;
import java.util.UUID;

public class AdminService {

    private final UserStorage userStorage = new JdbcUserStorage();

    public void create(String username, String password) {
        User admin = new User(username, password, Role.ADMIN);

        userStorage.add(admin);
    }

    public List<User> findAll() {
        return userStorage.getAll();
    }

    public boolean deleteUserById(UUID id) {
        return userStorage.deleteById(id);
    }
}

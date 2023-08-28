package by.tms.calculator.interfaces;

import by.tms.calculator.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserStorage {
    void add(User user);

    User get(UUID id);

    Optional<User> get(String username, String password);
}

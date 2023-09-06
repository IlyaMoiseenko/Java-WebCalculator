package by.tms.calculator.interfaces;

import by.tms.calculator.models.User;

public interface Validation {
    boolean validate(User user);
    boolean passwordValidation(String password);
    boolean usernameValidation(String username);
}

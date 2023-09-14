package by.tms.calculator.utils;

import by.tms.calculator.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$");
    private final Pattern USERNAME_PATTERN = Pattern.compile("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");

    public boolean validate(User user) {
        return passwordValidation(user.getPassword()) &&
                usernameValidation(user.getUsername());
    }

    public boolean passwordValidation(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);

        if (matcher.matches())
            return true;

        throw new RuntimeException("Invalid password!");
    }

    public boolean usernameValidation(String username) {
        Matcher matcher = USERNAME_PATTERN.matcher(username);

        if (matcher.matches())
            return true;

        throw new RuntimeException("Invalid username");
    }
}

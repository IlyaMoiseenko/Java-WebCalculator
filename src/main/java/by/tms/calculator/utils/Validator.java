package by.tms.calculator.utils;

import by.tms.calculator.interfaces.Validation;
import by.tms.calculator.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements Validation {
    private final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$";
    private final String USERNAME_PATTERN  = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean validate(User user) {
        return passwordValidation(user.getPassword()) &&
                usernameValidation(user.getUsername());
    }

    private boolean passwordValidation(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        if (matcher.matches())
            return true;
        else
            throw new RuntimeException("Invalid password!");
    }

    private boolean usernameValidation(String username) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);

        if (matcher.matches())
            return true;
        else
            throw new RuntimeException("Invalid username");
    }
}

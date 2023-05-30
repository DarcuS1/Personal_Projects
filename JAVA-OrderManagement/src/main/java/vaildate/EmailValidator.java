package vaildate;

import model.Users;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<Users> {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public void validate(Users t) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(t.getUserEmail()).matches()) {
            throw new IllegalArgumentException("Email is not a valid email!");
        }
    }
}

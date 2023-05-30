package vaildate;

import model.Users;

import java.util.regex.Pattern;

public class PhoneValidator implements Validator<Users> {
    private static final String PHONE_PATTERN = "^0[0-9]{9}";

    @Override
    public void validate(Users user) {
        String phoneString = String.valueOf(user.getUserPhone());
        if (!Pattern.matches(PHONE_PATTERN, phoneString)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }
}
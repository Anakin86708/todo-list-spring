package com.ariel.springweb;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

    private static final String EMAIL = "ariel@gmail.com";
    private static final String PASSWORD = "1234";

    public boolean validateUserData(String email, String password) {
        return email.equals(EMAIL) && password.equals(PASSWORD);
    }
}

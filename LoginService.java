package service;

import data.User;

public class LoginService {

    public static boolean checkParameters(User u, String login, String password) {
        boolean equals = false;
        if (u != null && u.getLogin().equals(login) && u.getPassword().equals(password)) {
            equals = true;
        }
        return equals;
    }
}

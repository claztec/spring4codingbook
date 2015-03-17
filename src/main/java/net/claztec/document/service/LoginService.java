package net.claztec.document.service;

/**
 * Created by Derek Choi on 2015. 3. 13..
 */
public class LoginService implements Login {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAuthorized(String email, String pass) {
        if (username.equals(email) && password.equals(pass)) {
            return true;
        }

        return false;

    }
}

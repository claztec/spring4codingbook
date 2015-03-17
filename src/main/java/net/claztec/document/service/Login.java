package net.claztec.document.service;

/**
 * Created by Derek Choi on 2015. 3. 13..
 */
public interface Login {
    public boolean isAuthorized(String email, String pass);
}

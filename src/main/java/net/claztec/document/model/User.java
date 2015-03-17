package net.claztec.document.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derek Choi on 2015. 3. 17..
 */
public class User {

    private String userId;
    private String email;
    private String password;
    private String name;

    private List<Document> documents = new ArrayList<Document>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}

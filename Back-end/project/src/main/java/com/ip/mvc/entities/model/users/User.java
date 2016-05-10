package com.ip.mvc.entities.model.users;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userID;
    private String email;
    private String password;
    private List<String> otherEmails = new ArrayList<>();

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public List<String> getOtherEmails() {
        return otherEmails;
    }

    public void setOtherEmails(List<String> otherEmails) {
        this.otherEmails = otherEmails;
    }
}

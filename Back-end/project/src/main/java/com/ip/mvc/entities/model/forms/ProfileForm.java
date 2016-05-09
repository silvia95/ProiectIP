package com.ip.mvc.entities.model.forms;

import com.ip.mvc.entities.model.users.User;

public class ProfileForm extends User {

    private String confirmedPassword;

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    @Override
    public String toString() {
        return "ProfileForm{" +
                "confirmedPassword='" + confirmedPassword + '\'' +
                "} " + super.toString();
    }
}

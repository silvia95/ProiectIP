package com.ip.mvc.entities.model.users;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {

    private String firstname;
    private String lastname;
    private String type;
    private List<String> departments = new ArrayList<>();

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public void addDepartment(String department) {
        departments.add(department);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "type='" + type + '\'' +
                ", departments=" + departments +
                '}';
    }
}

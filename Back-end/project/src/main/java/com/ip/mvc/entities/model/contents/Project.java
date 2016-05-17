package com.ip.mvc.entities.model.contents;

import com.ip.mvc.entities.model.users.Teacher;

import java.util.Date;
import java.util.List;

public class Project extends ScientificActivity {

    private String projectID;
    private String director;
    private String title;
    private String domain;
    private String startDate;
    private String finishDate;
    private String description;
    private List<Teacher> contributors;
    private int budget;
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID='" + projectID + '\'' +
                ", director='" + director + '\'' +
                ", title='" + title + '\'' +
                ", domain='" + domain + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", description='" + description + '\'' +
                ", contributors=" + contributors +
                ", budget=" + budget +
                "} " + super.toString();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Teacher> getContributors() {
        return contributors;
    }

    public void setContributors(List<Teacher> contributors) {
        this.contributors = contributors;
    }

}

package com.ip.mvc.entities.model.contents;

import com.ip.mvc.entities.model.users.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private String authorsText;
    private List<Teacher> authorsList = new ArrayList<>();


    public Project() {

    }

    public Project(ResultSet resultSet) throws SQLException {
        this.projectID = resultSet.getString(1);
        this.director = resultSet.getString(2);
        this.title = resultSet.getString(3);
        this.domain = resultSet.getString(4);
        this.startDate = resultSet.getString(5);
        this.finishDate = resultSet.getString(6);
        this.description = resultSet.getString(7);
        this.budget = resultSet.getInt(8);
    }

    public String getAuthorsText() {
        return authorsText;
    }

    public void setAuthorsText(String authorsText) {
        this.authorsText = authorsText;
        String[] authors = authorsText.split(",");
        for (String author : authors) {
            String[] names = author.split(" ");
            Teacher teacher = new Teacher(names[0], names[1]);
            this.authorsList.add(teacher);
        }
    }

    public List<Teacher> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<Teacher> authorsList) {
        this.authorsList = authorsList;
    }

    public void addAuthor(Teacher author) {
        this.authorsList.add(author);
    }

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

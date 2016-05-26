package com.ip.mvc.entities.model.contents;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Quotation {

    private String userID;
    private String articleID;
    private String text;
    private String year;
    private String articleName;
    private String location;
    private int authors;

    public Quotation() {

    }

    public Quotation(ResultSet resultSet) throws SQLException {
        this.articleID = resultSet.getString(1);
        this.text = resultSet.getString(2);
        this.year = resultSet.getString(3);
        this.articleName = resultSet.getString(4);
        this.location = resultSet.getString(5);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAuthors() {
        return authors;
    }

    public void setAuthors(int authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Quotation{" +
                "userID='" + userID + '\'' +
                ", articleID='" + articleID + '\'' +
                ", text='" + text + '\'' +
                ", year='" + year + '\'' +
                ", articleName='" + articleName + '\'' +
                ", location='" + location + '\'' +
                ", authors=" + authors +
                '}';
    }
}

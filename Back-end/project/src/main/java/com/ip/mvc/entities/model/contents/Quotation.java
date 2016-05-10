package com.ip.mvc.entities.model.contents;

public class Quotation {

    private String userID;
    private String articleID;
    private String text;
    private String year;

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

    @Override
    public String toString() {
        return "Quotation{" +
                "userID='" + userID + '\'' +
                ", articleID='" + articleID + '\'' +
                ", text='" + text + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}

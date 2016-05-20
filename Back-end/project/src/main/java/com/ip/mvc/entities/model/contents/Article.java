package com.ip.mvc.entities.model.contents;

import com.ip.mvc.entities.model.users.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Article extends ScientificActivity {

    private String articleID;
    private String userID;
    private String title;
    private List<Teacher> authors = new ArrayList<>();
    private String year;
    private String journalISSN;
    private String journalTitle;
    private String authorsText;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
            this.authors.add(teacher);
        }
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Teacher> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Teacher> authors) {
        this.authors = authors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getJournalISSN() {
        return journalISSN;
    }

    public void setJournalISSN(String journalISSN) {
        this.journalISSN = journalISSN;
    }


    @Override
    public String toString() {
        return "Article{" +
                "articleID='" + articleID + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", year='" + year + '\'' +
                ", journalISSN='" + journalISSN + '\'' +
                ", journalTitle='" + journalTitle + '\'' +
                ", authorsText='" + authorsText + '\'' +
                "} " + super.toString();
    }
}

package com.ip.mvc.entities.model.contents;

import com.ip.mvc.entities.model.users.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    private List<String> otherAuthors = new ArrayList<>();
    private List<Quotation> quotationList = new ArrayList<>();


    public Article() {

    }

    public Article(ResultSet resultSet) throws SQLException {
        setArticleID(resultSet.getString(1));
        setTitle(resultSet.getString(2));
        setYear(resultSet.getString(3));
        setJournalISSN(resultSet.getString(4));
        setJournalTitle(resultSet.getString(5));
        setScore(resultSet.getInt(6));
    }


    public List<Quotation> getQuotationList() {
        return quotationList;
    }

    public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public void addQuotation(Quotation quotation) {
        this.quotationList.add(quotation);
    }

    public List<String> getOtherAuthors() {
        return otherAuthors;
    }

    public void setOtherAuthors(List<String> otherAuthors) {
        this.otherAuthors = otherAuthors;
    }

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

    public void addAuthor(Teacher teacher) {
        authors.add(teacher);
    }


    @Override
    public String toString() {
        return "Article{" +
                "articleID='" + articleID + '\'' +
                ", userID='" + userID + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", year='" + year + '\'' +
                ", journalISSN='" + journalISSN + '\'' +
                ", journalTitle='" + journalTitle + '\'' +
                ", authorsText='" + authorsText + '\'' +
                ", otherAuthors=" + otherAuthors +
                ", quotationList=" + quotationList +
                "} " + super.toString();
    }
}

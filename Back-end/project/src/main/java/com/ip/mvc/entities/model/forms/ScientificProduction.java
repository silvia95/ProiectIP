package com.ip.mvc.entities.model.forms;

import com.ip.mvc.entities.model.users.Teacher;

import java.util.ArrayList;
import java.util.List;

public class ScientificProduction {

    private String name;
    private String authorsText;
    private List<Teacher> authors = new ArrayList<>();
    private String journalName;
    private String classification;
    private String articleType;
    private int fromScore;
    private int toScore;
    private String fromYear;
    private String toYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorsText() {
        return authorsText;
    }

    public void setAuthorsText(String authorsText) {
        this.authorsText = authorsText;
        if(authorsText.length() > 0) {
            String[] authors = authorsText.split(",");
            for (String author : authors) {
                String[] names = author.split(" ");
                Teacher teacher = new Teacher(names[0], names[1]);
                this.authors.add(teacher);
            }
        }
    }

    public List<Teacher> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Teacher> authors) {
        this.authors = authors;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public int getFromScore() {
        return fromScore;
    }

    public void setFromScore(int fromScore) {
        this.fromScore = fromScore;
    }

    public int getToScore() {
        return toScore;
    }

    public void setToScore(int toScore) {
        this.toScore = toScore;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    @Override
    public String toString() {
        return "ScientificProduction{" +
                "name='" + name + '\'' +
                ", authorsText='" + authorsText + '\'' +
                ", authors=" + authors +
                ", journalName='" + journalName + '\'' +
                ", classification='" + classification + '\'' +
                ", articleType='" + articleType + '\'' +
                ", fromScore=" + fromScore +
                ", toScore=" + toScore +
                '}';
    }
}

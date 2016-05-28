package com.ip.mvc.entities.model.contents;

import com.ip.mvc.entities.model.users.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Book {

    private String bookID;
    private String name;
    private int year;
    private int score;
    private List<Teacher> authors = new ArrayList<>();
    private String authorsText;

    public Book() {

    }

    public Book(ResultSet resultSet) throws SQLException {
        this.bookID = resultSet.getString("BOOK_ID");
        this.name = resultSet.getString("BOOK_NAME");
        this.year = resultSet.getInt("BOOK_YEAR");
        this.score = resultSet.getInt("SCORE");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Teacher> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Teacher> authors) {
        this.authors = authors;
    }

    public void addAuthor(Teacher author) {
        this.authors.add(author);
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score / Math.max(1, authors.size());
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", score=" + score +
                ", authors=" + authors +
                '}';
    }
}

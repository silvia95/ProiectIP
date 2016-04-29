package com.ip.mvc.entities.model.contents;

import com.ip.mvc.entities.model.users.Teacher;

import java.util.Date;
import java.util.List;

public class Article extends ScientificActivity {

    private String title;
    private List<Teacher> authors;
    private Date year;
    private String location;
    private List<Quotation> quotations;

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


    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }
}

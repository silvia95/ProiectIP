package com.ip.mvc.entities.model.contents;

import com.ip.mvc.entities.model.users.Teacher;

public class Quotation extends ScientificActivity {

    private Article article;
    private String text;
    private Teacher author;
    
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Teacher getAuthor() {
        return author;
    }

    public void setAuthor(Teacher author) {
        this.author = author;
    }
}

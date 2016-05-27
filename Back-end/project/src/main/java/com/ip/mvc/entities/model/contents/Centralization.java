package com.ip.mvc.entities.model.contents;

/**
 * Created by cristian on 26.05.2016.
 */
public class Centralization {

    private int userID;
    private int articlesABScore;
    private int articlesTotalScore;
    private int quotationsABScore;
    private int quotationsTotalScore;
    private boolean pass;
    private String actualType;
    private String futureType;

    public Centralization(int userID){
        this.userID = userID;
    }

    public void setArticlesABScore(int score) {
        this.articlesABScore = score;
    }

    public int getArticlesABScore() {
        return articlesABScore;
    }

    public void setArticlesTotalScore(int score) {
        this.articlesTotalScore = score;
    }

    public int getArticlesTotalScore() {
        return articlesTotalScore;
    }

    public void setPass(boolean pas) {
        this.pass = pas;
    }

    public boolean getPass() {
        return pass;
    }

    public void setActualType(String t) {
        this.actualType = t;
    }

    public String getActualType() {
        return actualType;
    }

    public void setFutureType(String t) {
        this.futureType = t;
    }

    public String getFutureType() {
        return futureType;
    }

    public int getUserID() {
        return userID;
    }

    public int getQuotationsABScore() {
        return quotationsABScore;
    }

    public void setQuotationsABScore(int quotationsABScore) {
        this.quotationsABScore = quotationsABScore;
    }

    public int getQuotationsTotalScore() {
        return quotationsTotalScore;
    }

    public void setQuotationsTotalScore(int quotationsTotalScore) {
        this.quotationsTotalScore = quotationsTotalScore;
    }
}

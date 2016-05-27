package com.ip.mvc.entities.model.contents;

import com.ip.mvc.entities.model.users.Teacher;

/**
 * Created by cristian on 26.05.2016.
 */
public class Centralization {

    private String userID;
    private int articlesABScore;
    private int articlesTotalScore;
    private int quotationsABScore;
    private int quotationsTotalScore;
    private boolean pass;
    private String actualType;
    private String futureType;
    private Teacher teacher;


    public Centralization(String userID){
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

    public String getUserID() {
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * @return
     */
    public int minArticlesAB(){
        if (actualType.startsWith("Lect")) {
            return 16;
        }  else {
            return 56;
        }
    }

    /**
     * @return
     */
    public int minArticlesTotal(){
        if (actualType.startsWith("Lect")) {
            return 32;
        }  else {
            return 56;
        }
    }

    /**
     * @return
     */
    public int minQuotationsAB(){
        if (actualType.startsWith("Lect")) {
            return 12;
        }  else {
            return 40;
        }
    }

    /**
     * @return
     */
    public int minQuotationsTotal(){
        if (actualType.startsWith("Lect")) {
            return 48;
        }  else {
            return 120;
        }
    }

    /**
     * Checks if teacher passes Articles AB Score
     * @return
     */
    public boolean passArticlesAB(){
        return articlesABScore >= minArticlesAB();
    }

    /**
     * Checks if teacher passes total Articles AB Score
     * @return
     */
    public boolean passArticlesTotal(){
        return articlesTotalScore >= minArticlesTotal();
    }

    /**
     * Checks if teacher passes Articles AB Score
     * @return
     */
    public boolean passQuotationsAB(){
        return quotationsABScore >= minQuotationsAB();
    }

    /**
     * Checks if teacher passes total Articles AB Score
     * @return
     */
    public boolean passQuotationsTotal(){
        return quotationsTotalScore >= minQuotationsTotal();
    }

}
